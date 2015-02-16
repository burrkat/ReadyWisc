package edu.parkside.cs.checklist;

/**
 * Created by krawchukd on 2/15/15.
 */
public class Checklist_Row {

    // Instance variables.
    private String title;
    private int progress;

    public Checklist_Row(){
        title = "Empty";
        progress = 0;
    }

    public Checklist_Row(String title, int progress)
    {
        this.title = title;
        this.progress = progress;
    }

    public void setTitle(String title){
        // 1. Check string length.
        // 2. Filter valid inputs.
        // 3. If 1 + 2 succeed then set string.

        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setProgress(int progress)
    {
        // 1. Check for valid input.
        // 2. Is the new setting reasonable?
        // 3. If so then set progress.

        this.progress = progress;
    }

    public int getProgress(){
        return this.progress;
    }
}
