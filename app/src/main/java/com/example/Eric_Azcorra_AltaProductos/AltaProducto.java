package com.example.Eric_Azcorra_AltaProductos;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AltaProducto extends Activity {
    private EditText et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_producto);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
    }


    public void alta(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        String nombre = et2.getText().toString();
        String precio = et3.getText().toString();
        String cantidad = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("id", id);
        registro.put("nombre", nombre);
        registro.put("precio", precio);
        registro.put("cantidad", cantidad);
        bd.insert("productos", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se cargaron los datos del producto",
                Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,precio,cantidad from productos where id=" + id
                        + "", null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe el producto con dicho id",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void baja(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        int cant = bd.delete("productos", "id=" + id + "", null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se ha dado de baja el producto con ese id",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un producto con ese id",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        String nombre = et2.getText().toString();
        String precio = et3.getText().toString();
        String cantidad = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("precio", precio);
        registro.put("cantidad", cantidad);
        int cant = bd.update("productos", registro, "id=" + id, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Se modificaron los datos del producto", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "No existe un producto con ese id",
                    Toast.LENGTH_SHORT).show();
    }

}
