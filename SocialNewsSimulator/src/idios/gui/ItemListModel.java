package idios.gui;

import idios.Item;
import idios.Simulation;
import idios.Topic;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class ItemListModel extends AbstractListModel<Item> {

    private Topic topic;
    private Simulation sim;
    
    public ItemListModel(Simulation sim, String topicName) {
        this.sim = sim;
        this.topic = sim.getTopic(topicName);
        topic.frontPage(sim.getRankingStrategy());
    }
    
    @Override
    public Item getElementAt(int index) {
        return topic.getCachedFrontPage().get(index);
    }

    @Override
    public int getSize() {
//        try {
//            return topic.getItemManager().getRecords().size();
//        } catch (Exception e) {
//            return 0;
//        }
        return Topic.NUM_ITEMS_ON_FRONT_PAGE;
    }

}
