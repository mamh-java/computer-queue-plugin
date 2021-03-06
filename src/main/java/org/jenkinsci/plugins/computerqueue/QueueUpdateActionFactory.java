/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jenkinsci.plugins.computerqueue;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.Computer;
import hudson.model.TransientComputerActionFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Updating a computer queue is the only purpose of this class
 * 
 * @author Lucie Votypkova
 */
@Extension
public class QueueUpdateActionFactory extends TransientComputerActionFactory{

    @Override
    public Collection<? extends Action> createFor(Computer computer) {
        List<Action> actions = new ArrayList<Action>();
        actions.add(new QueueUpdateAction(computer));
        return actions;
    }
    
}
