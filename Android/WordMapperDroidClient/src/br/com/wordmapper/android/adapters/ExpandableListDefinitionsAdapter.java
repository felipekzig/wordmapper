package br.com.wordmapper.android.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.service.container.DefinitionContainer;

public class ExpandableListDefinitionsAdapter extends BaseExpandableListAdapter {

    private Context context;

    private ArrayList<String> groups;

    private ArrayList<ArrayList<String>> children;

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    public ExpandableListDefinitionsAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<String>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }

    public void addItem(DefinitionContainer definition) {
    	if (!definition.getDefinition().isEmpty()){
		    if (!groups.contains(definition.getDictionary())) {
		        groups.add(definition.getDictionary());
		    }
		    int index = groups.indexOf(definition.getDictionary());
		    if (children.size() < index + 1) {
		        children.add(new ArrayList<String>());
		    }
		    children.get(index).addAll(definition.getDefinition());
    	}
    }

    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    // Return a child view. You can load your custom layout here.
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    	String definition = (String) getChild(groupPosition, childPosition);
        
    	if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_list, null);
        }
        
        TextView tv = (TextView) convertView.findViewById(R.id.child_name);
        tv.setText("   " + definition.toString());

        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    public String getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // Return a group view. You can load your custom layout here.
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_list, null);
        }	
  
        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        tv.setText(group);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

}
