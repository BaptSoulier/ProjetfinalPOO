package pyland.util.cmd;

import pyland.model.IDoor;
import pyland.model.IPlayer;
import pyland.model.IRoomNetwork;
import pyland.model.Key;
import pyland.model.RoomNetworkFactory;
import pyland.util.Direction;

class OpenCommand extends Command {

    // CONSTRUCTEURS

    OpenCommand(String[] args) {
        super(convertArgs(args));
    }

    // COMMANDES

    public void executeFor(IPlayer p) {
        if (p == null) {
            throw new AssertionError();
        }

        if (getArgument(0) != null) {
            IRoomNetwork net = RoomNetworkFactory.get();
            Direction dir = (Direction) getArgument(0);
            IDoor door = net.getDoor(p.getLocation(), dir);
            if (door != null) {
                if (door.isLocked()) {
                    Key key = p.getKey();
                    if (key != null) {
                        door.unlock(key);
                        setDescription("Vous venez d'ouvrir la porte.");
                    } else {
                        setDescription("Vous n'avez pas la clé !");
                    }
                } else {
                    setDescription("La porte est déjà ouverte !");
                }
            } else {
                setDescription("Mais... Il n'y a pas de porte par ici !?");
            }
        } else {
            setDescription("La direction indiquée n'existe pas...");
        }
    }

    // OUTILS

    private static Object[] convertArgs(String[] args) {
        if (args == null) {
            throw new AssertionError();
        }

        if (args.length == 0) {
            return new Object[] { null };
        } else {
            return new Object[] { Direction.valueOf(args[0]) };
        }
    }
}
