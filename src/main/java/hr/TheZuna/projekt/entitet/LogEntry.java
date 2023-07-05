package hr.TheZuna.projekt.entitet;

import hr.TheZuna.projekt.users.Loggable;

public class LogEntry <Entity extends Loggable>{
    private Entity entity;
    public LogEntry(Entity entity) {
        this.entity = entity;
    }
    public String logDescription(){
        return this.entity.entityDescription();
    }
}
