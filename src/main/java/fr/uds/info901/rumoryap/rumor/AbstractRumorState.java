package fr.uds.info901.rumoryap.rumor;

import java.util.List;

import fr.uds.info901.rumoryap.SocialLink;

/**
 * The class Rumor have 5 states :
 * - Believed and the people is 'activist' 
 * - Believed and the people doesn't care
 * - Not believed and the people doesn't care
 * - Not believed and the people is activist
 * - Don't even give a shit
 */
public abstract class AbstractRumorState {

	public abstract String getColorInGraph();
	public abstract void spread(List<SocialLink> friendList);
}
