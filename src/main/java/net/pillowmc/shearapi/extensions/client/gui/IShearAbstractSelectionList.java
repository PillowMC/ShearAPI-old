package net.pillowmc.shearapi.extensions.client.gui;

public interface IShearAbstractSelectionList {
	default int getWidth() {
		throw new AssertionError("Where are you mixin?");
	}

	default int getHeight() {
		throw new AssertionError("Where are you mixin?");
    }

	default int getTop() {
		throw new AssertionError("Where are you mixin?");
    }

	default int getBottom() {
		throw new AssertionError("Where are you mixin?");
    }

	default int getLeft() {
		throw new AssertionError("Where are you mixin?");
    }

	default int getRight() {
		throw new AssertionError("Where are you mixin?");
    }
}
