package com.sandra.service.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONValidator {
    private static final Logger LOGGER = Logger.getLogger( TaskController.class.getName() );
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATE_REMINDER= "dd/MM/YYYY HH:MM";

    private JSONValidator(){
    }

    public static boolean validator(Task json){
        if(json==null){
            LOGGER.log( Level.FINE, "JSON null" );
            return false;
        }
        if(json.getDescription()==null||json.getTitle()==null){
            LOGGER.log( Level.FINE, "No description");
            return false;
        }
        if(json.getDueDate()==null||!isDateValid(json.getDueDate())){
            LOGGER.log( Level.FINE, "Invalid due date");
            return false;
        }
        if((json.getCompletionDate()!=null)&&(!isDateValid(json.getCompletionDate()))){
            LOGGER.log( Level.FINE, "Invalid completion date");
            return false;

        }
        if(json.getReminder()==null||!isReminderValid(json.getReminder())){
            LOGGER.log( Level.FINE, "invalid reminder format" );
            return false;
        }
        if(json.getDone()==null){
            LOGGER.log( Level.FINE,"done cant be null" );
            return false;
        }
        if((json.getDone()&&json.getCompletionDate()==null)||(!json.getDone()&&json.getCompletionDate()!=null)){
            LOGGER.log( Level.FINE,"Inconsistency between getDone y completionDate" );
            return false;
        }

        return true;
    }
    public static boolean isReminderValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_REMINDER);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
