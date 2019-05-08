package javaBeans;

import java.io.IOException;

import org.neodatis.odb.Objects;


public class LlenarProductos {
	public static void main(String[] args) throws IOException {
		DBManager dbM = new DBManager();
		dbM.insertarElemento(new Producto(1, "Duruss Cobalt", 10, 3, 12));
		dbM.insertarElemento(new Producto(2, "Varlion Avant Carbon", 5, 2, 7));
		dbM.insertarElemento(new Producto(3, "Star Vie Pyramid R50", 20, 5, 25));
		dbM.insertarElemento(new Producto(4, "Dunlop Titan", 8, 3, 10));
		dbM.insertarElemento(new Producto(5, "Vision King", 7, 1, 10));
		dbM.insertarElemento(new Producto(6, "Slazenger Reflex Pro", 5, 2, 5));
		//dbM.insertarElemento(new Venta(,2));
	
		dbM.cerrarDB();
	}
}
