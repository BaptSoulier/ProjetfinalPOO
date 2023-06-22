package pyland.util.cmd;

import pyland.model.IKeyRoom;
import pyland.model.IPlayer;
import pyland.model.IRoom;
import pyland.model.Key;

class TakeCommand extends Command {

    // CONSTRUCTEURS

    TakeCommand(String[] args) {
        super();
    }

    // COMMANDES

    public void executeFor(IPlayer p) {
        if (p == null) {
            throw new AssertionError();
        }

        IRoom room = p.getLocation();
        if (room instanceof IKeyRoom) {
            IKeyRoom sr = (IKeyRoom) room;
            Key key = sr.getKey();
            if (key == null) {
                setDescription("Il n'y a rien à ramasser...");
            } else {
                p.pickUpKey(key);
                sr.removeKey();
                setDescription("Vous avez pris " + key.description() + ".");
            }
        } else {
            setDescription("Il n'y a rien à ramasser...");
        }
    }
}
