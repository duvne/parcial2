package clases;

public class productos {
    private String img;
    private String nombre;
    private  String detalles;

    public productos(String img, String nombre, String detalles) {
        this.img = img;
        this.nombre = nombre;
        this.detalles = detalles;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
