/*
 * Created on Aug 26, 2004 @ Erasmus University Rotterdam Copyright (c) Delft
 * University of Technology
 */
package nl.tudelft.simulation.dsol.swing.introspection.mapping;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renders Collection values in a human-readable manner.
 * <p>
 * Copyright (c) 2002-2024 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://simulation.tudelft.nl/dsol/manual/" target="_blank">DSOL Manual</a>. The DSOL
 * project is distributed under a three-clause BSD-style license, which can be found at
 * <a href="https://simulation.tudelft.nl/dsol/docs/latest/license.html" target="_blank">DSOL License</a>.
 * </p>
 * @author <a href="https://www.linkedin.com/in/peterhmjacobs">Peter Jacobs</a>.
 * @author <a href="https://github.com/averbraeck">Alexander Verbraeck</a>.
 * @author Niels Lang.
 */
public class CollectionRenderer extends DefaultTableCellRenderer
{
    /** */
    private static final long serialVersionUID = 20140831L;

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column)
    {
        List<Object> coll = new ArrayList<Object>((Collection<?>) value);
        StringBuilder content = new StringBuilder();
        content.append("Collection of ");
        if (coll.size() > 0)
        {
            content.append(getShortName(coll.get(0).getClass()));
        }
        else
        {
            content.append("?");
        }
        content.append(": ");
        for (int i = 0; i < coll.size(); i++)
        {
            if (coll.get(i) == null)
            {
                content.append("null; ");
            }
            else
            {
                content.append(coll.get(i).toString() + "; ");
            }
        }
        return new JLabel(content.toString());
    }

    /**
     * Returns the short name of a class.
     * @param clasz Class&lt;?&gt;; the class
     * @return the short name
     */
    private static String getShortName(final Class<?> clasz)
    {
        String name = clasz.getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }
}
