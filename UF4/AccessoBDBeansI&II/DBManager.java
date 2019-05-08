package javaBeans;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/*
 */
public class DBManager {
	private String file="Productos.DB";
	private ODB odb;
	public DBManager() {
		try {
			this.odb = ODBFactory.open(this.file);
		}catch(Exception e){
			System.out.println("Error al abrir el fichero: "+this.file+" comprueba que no este siendo usado.");
			System.exit(0);
		}

	}
	public void insertarElemento(Object elemento){
		this.odb.store(elemento);
	}
	public Pedido obtenerPedido(int id) {
		Objects<Pedido> objectPedido = this.odb.getObjects(Pedido.class);
		while(objectPedido.hasNext()){
			Pedido ped = objectPedido.next();
			if (ped.getNumeroPedido()==id) {
				return ped;
			}

		}
		System.out.println("No se ha encontrado el pedido");
		return null;
	}
	public void modifyProducto(int idPro, int cantidadRestar) {
		Producto pro = obtenerProducto(idPro);		
		if (pro!=null) {
			int stockTotal = pro.getStockActual()-cantidadRestar;
			pro.setStockActual2(stockTotal);
			odb.store(pro);
		}
	}
	public void modifyPedido(int idPed) {
		Pedido ped = obtenerPedido(idPed);		
		if (ped!=null) {
			ped.setCompletado2(true);
			odb.store(ped);
		}
	}
	public void modifyVenta(int idPed) {
		Venta ven = obtenerVenta(idPed,"pedido");		
		if (ven!=null) {
			ven.setEstado("Completada");
			odb.store(ven);
		}
	}
	
	public Producto obtenerProducto(int id) {
		Objects<Producto> objectProducto = this.odb.getObjects(Producto.class);
		while(objectProducto.hasNext()){
			Producto pro = objectProducto.next();
			if (pro.getIdProducto()==id) {
				return pro;
			}

		}
		System.out.println("No se ha encontrado el producto");
		return null;
	}
	public Venta obtenerVenta(int id,String mode) {
		Objects<Venta> objectVenta = this.odb.getObjects(Venta.class);
		
		while(objectVenta.hasNext()){
			Venta ven = objectVenta.next();
			switch(mode) {
			case "id":
				if (ven.getIdVenta()==id) {
					return ven;
				}
				break;
			case "pedido":
				if(ven.getIdPedido()!=null) {
				if (ven.getIdPedido()==id) {
					System.out.println("Venta Actualizada");
					return ven;
				}
				}
				break;
			}


		
		}

		return null;

	}
	public void verContenido(int mode) {

		Objects<Pedido> objectPedido = this.odb.getObjects(Pedido.class);
		Objects<Producto> objectProducto = this.odb.getObjects(Producto.class);
		Objects<Venta> objectVenta = this.odb.getObjects(Venta.class);
		listarContenido(mode,objectPedido,objectProducto,objectVenta);



	}
	public int numeroUltimoElemento(String type) {
		int ultPed = 0;
		if (type.equals("pedido")) {
			Objects<Pedido> objectPedido = this.odb.getObjects(Pedido.class);
			while(objectPedido.hasNext()){
				Pedido ped = objectPedido.next();
				if (ped.getNumeroPedido()>ultPed) {
					ultPed = ped.getNumeroPedido();
				}
			}
			return ultPed;
		}
		else if (type.equals("venta")) {
			Objects<Venta> objectVenta = this.odb.getObjects(Venta.class);
			while(objectVenta.hasNext()){
				Venta ven = objectVenta.next();
				if (ven.getIdVenta()>ultPed) {
					ultPed = ven.getIdVenta();
				}
			}
			return ultPed;
		}
		return 0;
	}

	public void listarContenido(int mode,Objects<Pedido> objectPedido,Objects<Producto> objectProducto,Objects<Venta> objectVenta) {
		switch(mode) {
		case 1:
			while(objectPedido.hasNext()){
				Pedido ped = objectPedido.next();
				System.out.println("ID: "+ped.getNumeroPedido()+", Cantidad: "+ped.getCantidad()+", Producto: "+ped.getProducto().getDesProducto()+", Completado: "+ped.isCompletado()+", Fecha: "+ped.getFecha().toString()+".");
			}
			break;
		case 2:
			while(objectProducto.hasNext()){
				Producto pro = objectProducto.next();
				System.out.println("ID: "+pro.getIdProducto()+", Desc: "+pro.getDesProducto()+", Stock Actual: "+pro.getStockActual()+", Stock Minimo: "+pro.getStockMinimo()+", Stock Estandart: "+pro.getStockEstandart()+".");
			}
			break;
		case 3:
			while(objectVenta.hasNext()){
				Venta ven = objectVenta.next();
				System.out.println("ID: "+ven.getIdVenta()+", Id Pedido: "+ven.getIdPedido()+", Producto: "+ven.getProducto().getDesProducto()+", Estado: "+ven.getEstado()+", Cantidad: "+ven.getCantidad()+", Fecha: "+ven.getFecha().toString()+".");
			}
			break;
		}
	}
	public void cerrarDB(){
		this.odb.close();
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public ODB getOdb() {
		return odb;
	}
	public void setOdb(ODB odb) {
		this.odb = odb;
	}
}
