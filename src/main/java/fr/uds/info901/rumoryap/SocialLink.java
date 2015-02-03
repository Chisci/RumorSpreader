package fr.uds.info901.rumoryap;


public class SocialLink {
	private double trustability;
	private Personne friend;
	
	public SocialLink(double trustability, Personne friend) {
		super();
		this.trustability = trustability;
		this.friend = friend;
	} 
	
	/**
	 * @return the trustability
	 */
	public double getTrustability() {
		return trustability;
	}
	/**
	 * @param trustability the trustability to set
	 */
	public void setTrustability(double trustability) {
		this.trustability = trustability;
	}
	/**
	 * @return the friend
	 */
	public Personne getFriend() {
		return friend;
	}
	/**
	 * @param friend the friend to set
	 */
	public void setFriend(Personne friend) {
		this.friend = friend;
	}
	
	public void forwardRumor(){
		this.friend.hearRumor(this.trustability);
	}

	

}
