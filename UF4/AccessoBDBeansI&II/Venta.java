package javaBeans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

/*
 * 
 */
public class Venta implements Serializable, PropertyChangeListener {
	private int idVenta;
	private Producto producto;
	private int cantidad;
	private String estado;
	private Integer idPedido;
    private Date fecha;
	
	public Venta() {};
	public Venta(int idVenta, Producto producto, int cantidad, String estado, Integer idPedido, Date fecha) {
		super();
		this.idVenta = idVenta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.estado = estado;
		this.idPedido = idPedido;
		this.fecha = fecha;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		DBManager dbM = new DBManager();
		int ultVenta = dbM.numeroUltimoElemento("venta")+1;
		Integer ultPedido = null;
		int idProducto = Integer.parseInt(evt.getOldValue().toString());//stockactual
		int cantidadCompraProducto = Integer.parseInt(evt.getNewValue().toString());//cantidadCompra
		this.producto = dbM.obtenerProducto(idProducto);
		if (evt.getPropertyName().equals("Venta")) {
			this.estado = "Venta Realizada";
    		dbM.insertarElemento(new Venta(ultVenta,this.producto,cantidadCompraProducto, estado ,ultPedido,new Date()));
    		System.out.println("Venta  Generada: "+ultVenta+", Producto: "+this.producto.getDesProducto()+", Cantidad: "+cantidadCompraProducto+", Pedido:"+ultPedido+".");
    	}else if (evt.getPropertyName().equals("PedidoVenta")) {
    		ultPedido = dbM.numeroUltimoElemento("pedido");
    		this.estado="Pendiente de compra por pedido num: "+ultPedido;
    		dbM.insertarElemento(new Venta(ultVenta,this.producto,cantidadCompraProducto, estado ,ultPedido,new Date()));
    		System.out.println("Venta  Generada: "+ultVenta+", Producto: "+this.producto.getDesProducto()+", Cantidad: "+cantidadCompraProducto+", Pedido:"+ultPedido+".");	
    	}else if (evt.getPropertyName().equals("UpdateVenta")) {

    			dbM.modifyVenta(idProducto);
    			int venta = dbM.obtenerVenta(idProducto, "pedido").getCantidad();
    			dbM.modifyProducto(cantidadCompraProducto, venta);
    		
    	}
		
		
		dbM.cerrarDB();
		String d ="d";
		
	}
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
