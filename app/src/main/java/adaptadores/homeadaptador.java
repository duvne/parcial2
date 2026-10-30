package adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menunavegacion.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import clases.productos;

public class homeadaptador extends RecyclerView.Adapter<homeadaptador.ViewHolder> {

    private List<productos> datos;

    public homeadaptador(List<productos> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public homeadaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull homeadaptador.ViewHolder holder, int position) {
        productos dato = datos.get(position);
        holder.bind(dato);
    }

    @Override
    public int getItemCount() {
        return datos != null ? datos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre_usuario, nombre_curso;
        ImageView img_usuario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_usuario = itemView.findViewById(R.id.nombre_usuario);
            nombre_curso = itemView.findViewById(R.id.nombre_curso);
            img_usuario = itemView.findViewById(R.id.img_usuario);
        }

        public void bind(productos dato) {
            nombre_usuario.setText(dato.getNombre());
            nombre_curso.setText(dato.getDetalles());
            Picasso.get().load(dato.getImg()).into(img_usuario);
        }
    }
}
