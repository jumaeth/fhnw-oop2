package guilib;

import io.github.humbleui.jwm.Event;
import io.github.humbleui.jwm.EventKey;
import io.github.humbleui.jwm.Key;
import io.github.humbleui.skija.Canvas;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Group extends Node {

    private final List<Node> children = new ArrayList<>();

    public Group(List<Node> children) {
        this.children.addAll(children);
    }

    public Group(Node... children) {
        this(asList(children));
    }

    /**
     * Returns a copy of the current list of children. To add or remove
     * nodes, use {@link #addChild(Node)} or {@link #removeChild(Node)}.
     */
    public List<Node> getChildren() {
        return new ArrayList<>(children);
    }

    public void addChild(Node child) {
        setDirty();
        children.add(child);
    }

    public boolean removeChild(Node child) {
        setDirty();
        return children.remove(child);
    }

    public void removeChildren() {
        setDirty();
        children.clear();
    }

    @Override
    protected void paint(Canvas canvas) {
        for (var child : children) {
            child.paint(canvas);
        }
    }

    @Override
    protected boolean clearDirty() {
        var anyDirty = super.clearDirty();
        for (Node child : children) {
            anyDirty |= child.clearDirty();
        }
        return anyDirty;
    }

    @Override
    protected void handleEvent(Event e) {
        for (Node child : children) {
            child.handleEvent(e);
        }
        if (e instanceof EventKey k && k.getKey() == Key.TAB && k.isPressed()) {
            Control firstFocusable = null;
            Control firstFocused = null;
            Control nextFocusable = null;
            for (Node child : children) {
                if (child instanceof Control c) {
                    if (c.isFocusable()) {
                        if (firstFocusable == null) {
                            firstFocusable = c;
                        }
                        if (c.isFocused() && firstFocused == null) {
                            firstFocused = c;
                        } else if (firstFocused != null) {
                            nextFocusable = c;
                            break;
                        }
                    }
                }
            }
            if (firstFocused != null) {
                firstFocused.setFocused(false);
            }
            if (nextFocusable == null) {
                nextFocusable = firstFocusable;
            }
            if (nextFocusable != null) {
                nextFocusable.setFocused(true);
            }
        }
    }
}
