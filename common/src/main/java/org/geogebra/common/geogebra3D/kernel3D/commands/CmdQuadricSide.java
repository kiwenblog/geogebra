package org.geogebra.common.geogebra3D.kernel3D.commands;

import org.geogebra.common.geogebra3D.kernel3D.geos.GeoQuadric3DLimited;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CommandProcessor;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.kernelND.GeoQuadricND;
import org.geogebra.common.main.MyError;

public class CmdQuadricSide extends CommandProcessor {

	public CmdQuadricSide(Kernel kernel) {
		super(kernel);
	}

	public GeoElement[] process(Command c) throws MyError {
		int n = c.getArgumentNumber();
		GeoElement[] arg;

		switch (n) {

		case 1:
			arg = resArgs(c);
			if (arg[0] instanceof GeoQuadric3DLimited) {
				return new GeoElement[] { kernelA.getManager3D().QuadricSide(
						c.getLabel(), (GeoQuadricND) arg[0]) };
			}
			throw argErr(arg[0]);

		default:
			throw argNumErr(n);
		}

	}

	protected MyError argErr(GeoElement geo) {
		return argErr(app, "QuadricSide", geo);
	}

	protected MyError argNumErr(int n) {
		return argNumErr(app, "QuadricSide", n);
	}

}
