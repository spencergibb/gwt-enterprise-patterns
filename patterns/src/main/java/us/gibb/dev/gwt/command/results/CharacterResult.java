package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class CharacterResult implements Result {
    private static final long serialVersionUID = -3551671146749279775L;
    private Character o;
    
    CharacterResult() {
    }

    public CharacterResult(Character o) {
        this.o = o;
    }
    
    public Character getCharacter() {
        return o;
    }
}
