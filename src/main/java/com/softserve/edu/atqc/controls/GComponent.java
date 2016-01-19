package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlSearch;

abstract class GComponent<TComponent> extends AComponent<TComponent> implements IComponent {

	// implements constructor

    protected GComponent() {
	}
        
	// implements interface

	public String getAttribute(String attribute) {
		return getControlWrapper().getAttribute(attribute);
	}

	public String getAttributeName() {
		return getControlWrapper().getAttributeName();
	}

	public String getContent() {
		return getControlWrapper().getContent();
	}

	public String getTagName() {
		return getControlWrapper().getTagName();
	}

	public boolean isDisplayed() {
		return getControlWrapper().isDisplayed();
	}

	public boolean isEnabled() {
		return getControlWrapper().isEnabled();
	}

	public boolean isSelected() {
		return getControlWrapper().isSelected();
	}

	public boolean isInvisible() {
        return ControlSearch.get().isInvisibleWebElement(getControlLocation());
    }

	public boolean isStatelessOf() {
		return ControlSearch.get().isStatelessOfWebElement(getControlWrapper());
	}

}
