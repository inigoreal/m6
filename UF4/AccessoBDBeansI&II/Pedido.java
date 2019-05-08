package javaBeans;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Pedido implements Serializable, PropertyChangeListener {
	public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
	private int numeroPedido;
	private Producto producto;
	private Date fecha;
	private int cantidad;
	private boolean completado;
	private PropertyChangeSupport propertySupport;

	public Pedido() {propertySupport = new PropertyChangeSupport (this);}

	public Pedido (int numeroPedido, Producto producto, Date fecha, int cantitad) {
		propertySupport = new PropertyChangeSupport (this);
		this.completado = false;
		this.numeroPedido = numeroPedido;
		this.producto = producto;
		this.fecha = fecha;
		this.cantidad = cantitad;
	}

	public void propertyChange(PropertyChangeEvent evt) {

		DBManager dbM = new DBManager();
		int ultPedido = dbM.numeroUltimoElemento("pedido")+1;
		int idproducto = Integer.parseInt(evt.getOldValue().toString());//idproducto
		int cantidadCompra = Integer.parseInt(evt.getNewValue().toString());//cantidadCompra
		Producto pro = dbM.obtenerProducto(idproducto);
		int pedidoMinimo = pro.getStockEstandart()-pro.getStockActual();
		int pedidoMinimoMasVenta = pedidoMinimo+cantidadCompra;
		int pedidoFinal = 0;
		if (evt.getPropertyName().equals("Pedido")) {
			dbM.insertarElemento(new Pedido(ultPedido,pro,new Date(),pedidoMinimo));
			pedidoFinal = pedidoMinimo;
			System.out.println("Pedido Generado: "+ultPedido+" del producto: "+pro.getDesProducto()+", Cantidad: "+pedidoFinal+".");
		}else if (evt.getPropertyName().equals("PedidoVenta")) {
			dbM.insertarElemento(new Pedido(ultPedido,pro,new Date(),pedidoMinimoMasVenta));
			pedidoFinal = pedidoMinimoMasVenta;
			System.out.println("Pedido Generado: "+ultPedido+" del producto: "+pro.getDesProducto()+", Cantidad: "+pedidoFinal+".");
		}
		//El pedido reune la cantidad comprada mas lo necesario para llegar a la cantidad estandart

		dbM.cerrarDB();
	}
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public boolean isCompletado() {
		return completado;
	}
	public void setCompletado(boolean completado,int cantidadRestar) {
			DBManager dbM = new DBManager();
			dbM.modifyProducto(this.producto.getIdProducto(), -cantidadRestar);
			dbM.modifyPedido(this.numeroPedido);
			dbM.cerrarDB();
			propertySupport.firePropertyChange("UpdateVenta", this.numeroPedido, this.producto.getIdProducto());
	}
	public void setCompletado2(boolean completado) {
		this.completado = completado;
	}


	public void addPropertyChangeListener (PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener (PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(listener);
	}

	public PropertyChangeSupport getPropertySupport() {
		return propertySupport;
	}

	public void setPropertySupport(PropertyChangeSupport propertySupport) {
		this.propertySupport = propertySupport;
	}

}
