package martic20.sqlite;

/**
 * Created by Martí on 11/12/2017.
 */

public class ModelVino {

    public static final String TABLE_VINOS = "vinos";
    public static final String VINOS_ID = "id";
    public static final String VINOS_TIPO = "tipo";
    public static final String VINOS_NOM = "nom";
    public static final String VINOS_ORIGEN = "origen";
    public static final String VINOS_COLLITA = "collita";


    public String nom, origen, collita, tipo;
    public Integer id;

    public ModelVino(Integer id, String nom,String tipo, String origen,  String collita) {
        this.id = id;
        this.nom = nom;
        this.origen = origen;
        this.collita = collita;
        this.tipo = tipo;

    }

    public int getId() {
        return this.id;
    }

    public static String updateById(Integer id, String nom,String tipo, String origen, String collita) {
        return "update " + TABLE_VINOS + " " +
                "set " + VINOS_NOM + "= '" + nom + "', " + VINOS_ORIGEN + "= '" + origen + "'," +
                VINOS_COLLITA + "= '" + collita + "'," + VINOS_TIPO + "= '" + tipo + "' " +
                "where " + ModelVino.VINOS_ID + " =" + id;
    }

    public static String insert(String nom,String tipo, String origen, String collita) {
        return "insert into " + TABLE_VINOS + " " +
                "(" + VINOS_NOM + ","  + VINOS_ORIGEN + ","  +
                VINOS_COLLITA + "," + VINOS_TIPO + ")" +
                " values ('" + nom + "','"+ tipo + "','"+ origen + "','"+ collita + "');";
    }
}
