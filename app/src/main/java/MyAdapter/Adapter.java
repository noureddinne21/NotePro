package MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nouroeddinne.notepro.HomeActivity;
import com.nouroeddinne.notepro.R;
import com.nouroeddinne.notepro.Show_EditActivity;

import java.util.List;

import Database.DataBaseHendler;
import Model.Note;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Note> noteList;
    private DataBaseHendler db;


    public Adapter(Context context, List<Note> listitems) {
        this.context = context;
        this.noteList = listitems;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(viwe);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note item = noteList.get(position);
        holder.title.setText(FormatTextNote(item.getTitel(),40));
        holder.note.setText(FormatTextNote(item.getNote(),100));
        holder.date.setText(item.getDate());

        db = new DataBaseHendler(context);
        if (item.getFavoraite()){
            holder.save.setImageResource(R.drawable.save_white);
        }else {
            holder.save.setImageResource(R.drawable.save);
        }

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newFavoriteStatus = !item.getFavoraite();
                item.setFavoraite(newFavoriteStatus);
                db.updateNote(item);
                notifyDataSetChanged();

                if (item.getFavoraite()){
                    holder.save.setImageResource(R.drawable.save_white);
                }else {
                    holder.save.setImageResource(R.drawable.save);
                }

            }
        });

        holder.linear_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Show_EditActivity.class);
                intent.putExtra("id", String.valueOf(item.getId()));
                intent.putExtra("title", item.getTitel());
                intent.putExtra("note", item.getNote());
                intent.putExtra("date", item.getDate());
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return noteList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,note,date;
        private ImageView save;
        private LinearLayout linear_show;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            note = itemView.findViewById(R.id.textView_note);
            date = itemView.findViewById(R.id.textView_date);
            save = itemView.findViewById(R.id.imageView_item_save);
            linear_show = itemView.findViewById(R.id.linear_show);
        }
    }



    public String FormatTextNote(String s,int n){

        int length = s.length();
        if(length>n){
            s = s.substring(0,n);
            s = s+"...";
            return s;
        }
        return s;
    }




















}
