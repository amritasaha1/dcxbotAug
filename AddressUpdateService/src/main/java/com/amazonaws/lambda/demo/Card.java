package com.amazonaws.lambda.demo;

public class Card {
	
	public String card;
	public String cardThumbnail;
	
	public Card(String card, String cardThumbnail) {
		this.card = card;
		this.cardThumbnail = cardThumbnail;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getCardThumbnail() {
		return cardThumbnail;
	}
	public void setCardThumbnail(String cardThumbnail) {
		this.cardThumbnail = cardThumbnail;
	}
	
	

}
