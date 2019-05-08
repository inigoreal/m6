package javaBeans;

import java.beans.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Producto implements Serializable {

	public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
	private int idProducto;
	private String desProducto;
	private int stockActual;
	private int stockMinimo;
	private int stockEstandart;
	
	
	private PropertyChangeSupport propertySupport;
	
	public Producto () {
		propertySupport = new PropertyChangeSupport (this);
    }
	
	public Producto (int idProducto, String desProducto, int stockActual, int stockMinimo, int stockEstandart) {
		propertySupport = new PropertyChangeSupport(this);
		this.idProducto = idProducto;
		this.desProducto = desProducto;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.stockEstandart = stockEstandart;
	}
	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDesProducto() {
		return desProducto;
	}

	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}

	public int getStockActual() {
		return stockActual;
	}
	
	public void setStockActual2(int stockActual) {
		this.stockActual = stockActual;
	}
	public void setStockActual(int cantidadCompra) {
		
		if (this.stockActual-cantidadCompra<0) {//no se puede realizar la compra, pero si la ventaPedido 
			propertySupport.firePropertyChange("PedidoVenta", this.idProducto, cantidadCompra);
		}else {
			this.stockActual = stockActual-cantidadCompra;
			DBManager dbM = new DBManager();
			dbM.modifyProducto(this.idProducto, cantidadCompra);
			dbM.cerrarDB();
			
			propertySupport.firePropertyChange("Venta", this.idProducto, cantidadCompra);
			if (this.stockActual-cantidadCompra<=this.stockMinimo) {//se realiza la compra y se pide pedido
				propertySupport.firePropertyChange("Pedido", this.idProducto, cantidadCompra);
				
			}
			
			
		}
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getStockEstandart() {
		return stockEstandart;
	}

	public void setStockEstandart(int stockEstandart) {
		this.stockEstandart = stockEstandart;
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
