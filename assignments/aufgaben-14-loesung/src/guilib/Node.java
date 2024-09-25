package guilib;

import io.github.humbleui.jwm.Event;
import io.github.humbleui.skija.Canvas;

import java.util.Objects;

public abstract class Node {

    private boolean dirty = true;

    /**
     * Returns <code>true</code> if this node is dirty and resets the
     * dirty flag.
     */
    protected boolean clearDirty() {
        try {
            return dirty;
        } finally {
            dirty = false;
        }
    }

    /**
     * Sets the dirty flag. Should be used by subclasses when the node
     * is "dirty" and needs to be repainted in the next frame.
     */
    protected void setDirty() {
        this.dirty = true;
    }

    /**
     * Helper method to be used by subclasses' setters for properties
     * that affect the appearance of the node. Compares the two values
     * and sets the dirty flag if they are not equal.
     */
    protected void updateDirty(Object currentValue, Object newValue) {
        if (!Objects.equals(currentValue, newValue)) {
            this.dirty = true;
        }
    }

    protected abstract void paint(Canvas canvas);

    protected abstract void handleEvent(Event e);
}
