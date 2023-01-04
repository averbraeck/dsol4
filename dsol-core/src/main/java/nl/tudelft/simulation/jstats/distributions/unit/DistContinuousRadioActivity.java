package nl.tudelft.simulation.jstats.distributions.unit;

import org.djunits.unit.RadioActivityUnit;
import org.djunits.value.vdouble.scalar.RadioActivity;

import nl.tudelft.simulation.jstats.distributions.DistContinuous;

/**
 * DistContinuousRadioActivity is class defining a distribution for a RadioActivity scalar. <br>
 * <br>
 * Copyright (c) 2003-2023 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://www.simulation.tudelft.nl/" target="_blank">www.simulation.tudelft.nl</a>. The
 * source code and binary code of this software is proprietary information of Delft University of Technology.
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DistContinuousRadioActivity extends DistContinuousUnit<RadioActivityUnit, RadioActivity>
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new continuous distribution that draws RadioActivity scalars.
     * @param wrappedDistribution DistContinuous; the wrapped continuous distribution
     * @param unit RadioActivityUnit; the unit for the values of the distribution
     */
    public DistContinuousRadioActivity(final DistContinuous wrappedDistribution, final RadioActivityUnit unit)
    {
        super(wrappedDistribution, unit);
    }

    /**
     * Constructs a new continuous distribution that draws RadioActivity scalars in SI units.
     * @param wrappedDistribution DistContinuous; the wrapped continuous distribution
     */
    public DistContinuousRadioActivity(final DistContinuous wrappedDistribution)
    {
        super(wrappedDistribution, RadioActivityUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public RadioActivity draw()
    {
        return new RadioActivity(this.wrappedDistribution.draw(), this.unit);
    }
}
