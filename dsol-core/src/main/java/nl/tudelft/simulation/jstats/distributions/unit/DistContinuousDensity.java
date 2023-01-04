package nl.tudelft.simulation.jstats.distributions.unit;

import org.djunits.unit.DensityUnit;
import org.djunits.value.vdouble.scalar.Density;

import nl.tudelft.simulation.jstats.distributions.DistContinuous;

/**
 * DistContinuousDensity is class defining a distribution for a Density scalar. <br>
 * <br>
 * Copyright (c) 2003-2023 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://www.simulation.tudelft.nl/" target="_blank">www.simulation.tudelft.nl</a>. The
 * source code and binary code of this software is proprietary information of Delft University of Technology.
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DistContinuousDensity extends DistContinuousUnit<DensityUnit, Density>
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new continuous distribution that draws Density scalars.
     * @param wrappedDistribution DistContinuous; the wrapped continuous distribution
     * @param unit DensityUnit; the unit for the values of the distribution
     */
    public DistContinuousDensity(final DistContinuous wrappedDistribution, final DensityUnit unit)
    {
        super(wrappedDistribution, unit);
    }

    /**
     * Constructs a new continuous distribution that draws Density scalars in SI units.
     * @param wrappedDistribution DistContinuous; the wrapped continuous distribution
     */
    public DistContinuousDensity(final DistContinuous wrappedDistribution)
    {
        super(wrappedDistribution, DensityUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public Density draw()
    {
        return new Density(this.wrappedDistribution.draw(), this.unit);
    }
}
