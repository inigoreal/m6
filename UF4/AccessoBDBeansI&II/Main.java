package javaBeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.neodatis.odb.Objects;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


		int mode=0;
		while(mode!=4) {
			DBManager dbM = new DBManager();	
			System.out.println(" --------------------");
			System.out.println("|Ver Contenido    [1]|");
			System.out.println("|Realizar Compra  [2]|");
			System.out.println("|Completar Pedido [3]|");
			System.out.println("|Salir            [4]|");
			System.out.println(" --------------------");

			mode = Integer.parseInt(reader.readLine());

			switch(mode){
			case 1:
				System.out.println(" -----------------");
				System.out.println("|Ver Pedidos   [1]|");
				System.out.println("|Ver Productos [2]|");
				System.out.println("|Ver Ventas    [3]|");
				System.out.println(" -----------------");
				dbM.verContenido(Integer.parseInt(reader.readLine()));
				dbM.cerrarDB();
				break;
			case 2:
				Pedido pedido = new Pedido();
				Venta venta = new Venta();
				System.out.print("Id Producto a comprar: ");
				Producto pro = dbM.obtenerProducto(Integer.parseInt(reader.readLine()));				
				dbM.cerrarDB();
				System.out.print("Cantidad: ");

				pro.addPropertyChangeListener(pedido);
				pro.addPropertyChangeListener(venta);
				pro.setStockActual(Integer.parseInt(reader.readLine()));


				break;
			case 3:
				Venta venta1 = new Venta();
				System.out.print("Id Pedido a completar: ");
				Pedido ped = dbM.obtenerPedido(Integer.parseInt(reader.readLine()));
				dbM.cerrarDB();
				if(ped!=null && !ped.isCompletado()) {
					ped.addPropertyChangeListener(venta1);
					ped.setCompletado(true,ped.getCantidad());
				}

				break;
			case 4:
				dbM.cerrarDB();
				System.exit(0);
				break;
			default:
				dbM.cerrarDB();
				break;
			}
		}
	}

}
