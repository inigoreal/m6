


public class Prueba {
    public static void main(String[] args) {

        Empleado emple = new Empleado();
        PanelEmpleado panel = new PanelEmpleado();
        panel.setEmpleado(emple);
        
        emple.addPropertyChangeListener(panel);
        emple.setSueldo(1200);
        System.out.println(emple.getSueldo());
        
        emple.setSueldo(1100);
        System.out.println(emple.getSueldo());
        
        emple.setSueldo(1200);
        System.out.println(emple.getSueldo());
        
        emple.setCargo("CEO");
        System.out.println(emple.getCargo());
        
        emple.setCargo("Cliente");
        System.out.println(emple.getCargo());
        
    }
}


