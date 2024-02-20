package com.project.javaecommerce.services;

import com.project.javaecommerce.entities.*;
import com.project.javaecommerce.repositories.ClienteRepository;
import com.project.javaecommerce.repositories.ProductoRepository;
import com.project.javaecommerce.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VentaService {
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private ProductoRepository prodRepo;
    @Autowired
    private VentaRepository ventaRepo;
    //@Autowired
    //private RestTemplate restTemplate;

    // MÉTODOS HTTP --------------------------------------------------------------------------------
    // GET ----------------------
    public List<VentaDTO> obtenerVentasDTO () { return fabricarVentasDTO(ventaRepo.findAll()); }
    public VentaDTO obtenerVentaDTO (Long id) {
        Optional<Venta> ventaAux = ventaRepo.findById(id);
        if (ventaAux.isPresent()) {
            return fabricarVentaDTO(ventaAux.get());
        } else {
            return new VentaDTO();
        }
    }

    // POST ---------------------
    public VentaDTO crearVentaDTO (Venta venta) {
        Boolean hayCliente = existeCliente(venta.getCliente());
        Boolean hayProductos = existenProductos(venta.getDetallesVenta());
        Boolean hayStock = alcanzaStock(venta.getDetallesVenta());
        if (hayCliente&&hayProductos&&hayStock) {
            Venta nuevaVenta = construirVenta(venta);
            return fabricarVentaDTO(ventaRepo.save(nuevaVenta));
        } else {
            return new VentaDTO();
        }
    }

    // PUT ----------------------
    public VentaDTO modificarVentaDTO (Long id, Venta venta) {
        Boolean hayCliente = existeCliente(venta.getCliente());
        Boolean hayProductos = existenProductos(venta.getDetallesVenta());
        Boolean hayStock = alcanzaStock(venta.getDetallesVenta());
        if (hayCliente&&hayProductos&&hayStock) {
            Venta updatedVenta = construirVenta(venta);
            updatedVenta.setId(id);
            return fabricarVentaDTO(ventaRepo.save(updatedVenta));
        } else {
            return new VentaDTO();
        }
    }

    // DELETE -------------------
    public void eliminarVentas () { ventaRepo.deleteAll(); }
    public void eliminarVenta (Long id) { ventaRepo.deleteById(id); }

    // MÉTODOS PRIVADOS ----------------------------------------------------------------------------
    // PASAJES A DTO <----
    private List<VentaDTO> fabricarVentasDTO (List<Venta> ventas) {
        List<VentaDTO> ventasDTO = new ArrayList<VentaDTO>();
        for (int i = 0; i < ventas.size(); i++) {
            Venta venta = ventas.get(i);
            ventasDTO.add(this.fabricarVentaDTO(venta));
        }
        return ventasDTO;
    }
    private VentaDTO fabricarVentaDTO (Venta venta) {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setId(venta.getId());
        ventaDTO.setFecha(venta.getFecha());
        ventaDTO.setTotal(venta.getTotal());
        ventaDTO.setCliente(venta.getCliente());
        ventaDTO.setDetallesVenta(venta.getDetallesVenta());
        //ventaDTO.setDetallesVenta(fabricarDetalleVentaDTO(venta.getDetallesVenta()));
        return ventaDTO;
    }

    private Set<DetalleVentaDTO> fabricarDetalleVentaDTO (Set<DetalleVenta> detalleVentas) {
        Set<DetalleVentaDTO> detalleVentaDTOs = new HashSet<DetalleVentaDTO>();
        for (DetalleVenta detalleVenta : detalleVentas) {
            DetalleVentaDTO dto = new DetalleVentaDTO();
            dto.setId(detalleVenta.getId());
            dto.setVenta(detalleVenta.getVenta());
            dto.setProducto(detalleVenta.getProducto());
            dto.setNombreProducto(detalleVenta.getNombreProducto());
            dto.setColorProducto(detalleVenta.getColorProducto());
            dto.setCantidadProducto(detalleVenta.getCantidadProducto());
            dto.setPrecioProducto(detalleVenta.getPrecioProducto());
            detalleVentaDTOs.add(dto);
        }
        return detalleVentaDTOs;
    }

    // VALIDACIONES <----
    private Boolean existeCliente (Cliente cliente) {
        Optional<Cliente> aux = clienteRepo.findById(cliente.getId());
        return !aux.isEmpty();
    }
    private Boolean existenProductos (Set<DetalleVenta> detalleVentas) {
        for (DetalleVenta detalleVenta : detalleVentas) {
            Long prodId = detalleVenta.getProducto().getId();
            Optional<Producto> prodAux = prodRepo.findById(prodId);
            if (prodAux.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private Boolean alcanzaStock (Set<DetalleVenta> detalleVentas) {
        for (DetalleVenta detalleVenta : detalleVentas) {
            Long prodId = detalleVenta.getProducto().getId();
            Optional<Producto> prodAux = prodRepo.findById(prodId);
            if (prodAux.isEmpty()) {
                return false;
            }
            if (detalleVenta.getCantidadProducto() <= prodAux.get().getStock()) {
                return true;
            }
        }
        return false;
    }

    // CONSTRUCCIÓN DEL REGISTRO VENTA A GUARDAR <----
    private Venta construirVenta (Venta venta) {
        var nuevoRegistroVenta = new Venta();

        // relacionar la venta con el cliente asociado
        nuevoRegistroVenta.setCliente(clienteRepo.findById(venta.getCliente().getId()).get());

        // capturar la fecha y hora de la realización de la venta
        //WorldClock worldClock = restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", WorldClock.class);
        //String fechaYHoraActual = worldClock.getCurrentDateTime();
        //try {
        //    Date date1=new SimpleDateFormat("yyyy-MM-dd'T'mm:ss'Z'").parse(fechaYHoraActual);
        //    nuevoRegistroVenta.setFecha(date1);
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //    nuevoRegistroVenta.setFecha(new Date());
        //}

        // DEBIDO A QUE EL IDE ME MARCA COMO ERRÓNEO EL USO DEL RESTTEMPLATE, UTILIZARÉ DATE DE JAVA PARA CAPTURAR LA FECHA
        nuevoRegistroVenta.setFecha(new Date());

        nuevoRegistroVenta.setDetallesVenta(new HashSet<DetalleVenta>());
        for (DetalleVenta detalleVenta : venta.getDetallesVenta()) {
            nuevoRegistroVenta.addDetalleVenta(construirDetalleVenta(detalleVenta));
        }

        // setear total
        nuevoRegistroVenta.setTotal(calcularTotal(nuevoRegistroVenta.getDetallesVenta()));
        // actualizar stock
        actualizarStock(nuevoRegistroVenta.getDetallesVenta());

        return nuevoRegistroVenta;
    }
    private DetalleVenta construirDetalleVenta (DetalleVenta detalleVenta) {
        DetalleVenta nuevoRegistroDV = new DetalleVenta();
        Producto productoAux = prodRepo.findById(detalleVenta.getProducto().getId()).get();
        nuevoRegistroDV.setProducto(productoAux);
        nuevoRegistroDV.setNombreProducto(productoAux.getNombre());
        nuevoRegistroDV.setColorProducto(productoAux.getColor());
        nuevoRegistroDV.setCantidadProducto(nuevoRegistroDV.getCantidadProducto());
        nuevoRegistroDV.setPrecioProducto(productoAux.getPrecio());
        return nuevoRegistroDV;
    }
    private Float calcularTotal (Set<DetalleVenta> detalleVentas) {
        Float total = 0.0F;
        for (DetalleVenta detalleVenta : detalleVentas) {
            total += (detalleVenta.getPrecioProducto()*detalleVenta.getCantidadProducto().floatValue());
        }
        return total;
    }
    private void actualizarStock (Set<DetalleVenta> detalleVentas) {
        for (DetalleVenta detalleVenta : detalleVentas) {
            Integer cantVendida = detalleVenta.getCantidadProducto();
            Producto productoAActualizar = detalleVenta.getProducto();
            Producto productoEnDB = prodRepo.findById(productoAActualizar.getId()).get();
            Integer nuevoStock = productoEnDB.getStock();
            nuevoStock -= cantVendida;
            productoEnDB.setStock(nuevoStock);
            prodRepo.save(productoEnDB);
        }
    }
}