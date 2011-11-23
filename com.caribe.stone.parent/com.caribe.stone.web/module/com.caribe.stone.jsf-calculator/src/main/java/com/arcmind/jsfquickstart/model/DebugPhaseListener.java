package com.arcmind.jsfquickstart.model;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class DebugPhaseListener implements PhaseListener {

	public void beforePhase(PhaseEvent phaseEvent) {
		System.out.println("------ BEFORE PHASE " + phaseEvent.getPhaseId());
	}

	public void afterPhase(PhaseEvent phaseEvent) {
		System.out.println("------ AFTER PHASE " + phaseEvent.getPhaseId());

		if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE) {
			System.out.println("REQUEST END\n\n");
		}

	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
