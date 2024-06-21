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
    private boolean status =false;
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
        holder.title.setText(FormatTextNote(item.getTitel(),0));
        holder.note.setText(FormatTextNote(item.getNote(),1));
        //holder.date.setText(item.getDate());
        holder.date.setText(String.valueOf(item.getFavoraite()));
        //Toast.makeText(context, String.valueOf(item.getFavoraite()), Toast.LENGTH_SHORT).show();

        db = new DataBaseHendler(context);
        status = item.getFavoraite();
        if (status){
            holder.save.setImageResource(R.drawable.save_orange);
        }else {
            holder.save.setImageResource(R.drawable.save);
        }

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,String.valueOf(db.updateFavorite(item.getId(),true)), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, String.valueOf(status), Toast.LENGTH_SHORT).show();
                if (status){
                    holder.save.setImageResource(R.drawable.save);
                    Toast.makeText(context,String.valueOf(db.updateFavorite(item.getId(),true)), Toast.LENGTH_SHORT).show();
                }else {
                    holder.save.setImageResource(R.drawable.save_orange);
                    Toast.makeText(context,String.valueOf(db.updateFavorite(item.getId(),true)), Toast.LENGTH_SHORT).show();
                }
                HomeActivity.refsh();
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






    public String FormatTextNote(String text,int num){

        int numberCharachter;
        if (num==0){
            numberCharachter=40;
        }else{
            numberCharachter=140;
        }

        String s[] = text.split("\\n");
        String newText=s[0]+"...",shortText="";
        int lengthText = newText.length();

        if (lengthText>numberCharachter){
            for (int i=0;i<numberCharachter;i++){
                if(i<numberCharachter-3){
                    shortText = shortText + newText.charAt(i);
                }else {
                    shortText = shortText + ".";
                }
            }
            return shortText;
        }else {
            return newText;
        }
    }






















}
