package com.illinois.safetyratingsuiuc;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    /**
     * Builds a new expandable list adapter.
     * For more information, refer to https://developer.android.com/reference/android/widget/ExpandableListAdapter.
     *
     * @param context allows access to app-specific resources and classes
     * @param expandableListTitle the title of an element of the expandable list
     * @param expandableListDetail the details of the expandable list element once clicked on
     */
    public MyExpandableListAdapter(Context context, List<String> expandableListTitle,
                                   HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    /**
     * Retrieves the data associated with a child element within the provided group.
     *
     * @param listPosition position of the group (parent) element
     * @param expandedListPosition position of the child element
     * @return the child element corresponding to a given parent
     */
    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    /**
     * Retrieves the ID for a child element within the provided group.
     *
     * @param listPosition position of the group (parent) element
     * @param expandedListPosition position of the child element
     * @return the ID of the child element
     */
    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    /**
     * Retrieves a View that displays data for a child element within the provided group.
     *
     * @param listPosition position of the group (parent) element
     * @param expandedListPosition position of the child element
     * @param isLastChild flag that outputs true if element is the last child, false otherwise
     * @param convertView the old View to reuse, if possible
     * @param parent the parent view
     * @return the view containing data for a child element
     */
    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    /**
     * Gets the number of children for a specific group.
     *
     * @param listPosition the position of the group
     * @return the number of children in that group
     */
    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    /**
     * Retrieves data associated with a group.
     *
     * @param listPosition the position of the group
     * @return the data for a particular group
     */
    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    /**
     * Get number of groups.
     *
     * @return number of groups
     */
    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    /**
     * Get the ID for a specified group.
     *
     * @param listPosition the position of a group
     * @return the ID for a particular group.
     */
    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    /**
     * Retrieves a View that displays the specified group.
     *
     * @param listPosition the position of a group
     * @param isExpanded true if the view (list) is expanded, false otherwise
     * @param convertView the old View to reuse, if possible
     * @param parent the parent view
     * @return the specified group
     */
    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    /**
     * Tells whether the child and group IDs are stable across changes to the underlying data.
     *
     * @return false if not stable, true otherwise
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Indicates whether the child of the specified position is selectable.
     *
     * @param listPosition the position of the group
     * @param expandedListPosition the position of the child
     * @return true if child is selectable, false otherwise
     */
    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
