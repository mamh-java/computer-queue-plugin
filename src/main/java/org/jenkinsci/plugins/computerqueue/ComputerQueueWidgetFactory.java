package org.jenkinsci.plugins.computerqueue;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.Computer;
import hudson.model.Node;
import hudson.model.Queue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jenkins.model.queue.QueueItem;
import jenkins.widgets.BuildQueueWidget;
import jenkins.widgets.WidgetFactory;
import org.jenkinsci.Symbol;

@Extension(ordinal = 110) @Symbol("buildQueueAgent")
public class ComputerQueueWidgetFactory extends WidgetFactory<Computer, BuildQueueWidget> {

    @Override
    public Class<Computer> type() {
        return Computer.class;
    }

    @Override
    public Class<BuildQueueWidget> widgetType() {
        return BuildQueueWidget.class;
    }

    @NonNull
    @Override
    public Collection<BuildQueueWidget> createFor(@NonNull Computer target) {
        List<QueueItem> buildableItems = new ArrayList<>();
        Node node = target.getNode();
        Queue.Item[] items = Queue.getInstance().getItems();
        for (Queue.Item item : items) {
            if (item instanceof Queue.BuildableItem item1) {
                if (node != null && node.canTake(item1) == null)
                    buildableItems.add(item1);
            }
        }

        return List.of(new BuildQueueWidget(target.getUrl(), buildableItems));
    }
}

