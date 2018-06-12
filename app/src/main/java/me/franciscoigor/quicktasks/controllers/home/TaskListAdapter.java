package me.franciscoigor.quicktasks.controllers.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.franciscoigor.quicktasks.R;
import me.franciscoigor.quicktasks.models.Task;

public class TaskListAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private HomeActivity activity;
    private ArrayList<Task> items;
    private DataSetObserver observer;

    public static final String EXTRA_TASK = "task";

    public TaskListAdapter(HomeActivity activity){
        super();
        this.activity = activity;
        this.context = activity.getApplicationContext();
        items = new ArrayList<Task>();
    }

    public DataSetObserver getObserver() {
        if (observer==null){
            observer=new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                }

                @Override
                public void onInvalidated() {
                    super.onInvalidated();
                }
            };
        }
        return observer;
    }

    public void addTask(Task task){
        items.add(task);
        this.notifyDataSetChanged();
    }

    public int getCompletedCount(){
        int count=0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCompleted()){
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        this.observer=observer;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        System.out.println("*** unregisterDataSetObserver");
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public Dialog getDialog(View taskDialogView,final Task item, int position){
        EditText title=taskDialogView.findViewById(R.id.form_title);
        title.setText(item.getTitle());
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CheckBox completed=taskDialogView.findViewById(R.id.form_completed);
        completed.setChecked(item.isCompleted());
        completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setCompleted(isChecked);
            }
        });

        EditText desc=taskDialogView.findViewById(R.id.form_desc);
        desc.setText(item.getDescription());
        desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return new AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.task_dialog_title,position+1))
                .setPositiveButton(android.R.string.ok, null)
                .setView(taskDialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(item);
                        activity.updateView();
                        TaskListAdapter.this.notifyDataSetChanged();
                    }
                })
                .create();

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItem=inflater.inflate(R.layout.list_item_home, null);
        final View taskDialogView=inflater.inflate(R.layout.form_item_home, null);
        final Task item= (Task)getItem(position);

        listItem.setClickable(true);

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=getDialog(taskDialogView, item, position);
                dialog.show();
            }
        });

        TextView title= listItem.findViewById(R.id.item_title);
        title.setText(item.getTitle());

        TextView desc= listItem.findViewById(R.id.item_desc);
        desc.setText(item.getDescription());

        CheckBox check= listItem.findViewById(R.id.item_completed);
        check.setChecked(item.isCompleted());
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setCompleted(isChecked);
                TaskListAdapter.this.notifyDataSetChanged();
                activity.updateView();
            }
        });

        return listItem;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
