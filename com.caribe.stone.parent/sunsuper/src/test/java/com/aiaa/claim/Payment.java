package com.aiaa.claim;

public class Payment {
	private String from;
	private String to;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Payment(String from, String to) {
		super();
		this.from = from;
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	@Override
	public String toString() {
		return "Payment [from=" + from + ", to=" + to + "]";
	}

	public void setTo(String to) {
		this.to = to;
	}
}
