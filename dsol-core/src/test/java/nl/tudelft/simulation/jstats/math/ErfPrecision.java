package nl.tudelft.simulation.jstats.math;

import java.util.function.DoubleFunction;

/**
 * Erf function precision test.
 * <p>
 * Copyright (c) 2021-2022 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://simulation.tudelft.nl/dsol/manual/" target="_blank">DSOL Manual</a>. The DSOL
 * project is distributed under a three-clause BSD-style license, which can be found at
 * <a href="https://simulation.tudelft.nl/dsol/3.0/license.html" target="_blank">DSOL License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class ErfPrecision
{
    /** test data from https://keisan.casio.com/exec/system/1180573450. x, erf(x), erfc(x) */
    private static final double[] TESTDATA = new double[] {0, 0, 1, 0.01, 0.01128341556, 0.9887165844, 0.02, 0.02256457469,
            0.9774354253, 0.03, 0.03384122234, 0.9661587777, 0.04, 0.04511110615, 0.9548888939, 0.05, 0.0563719778,
            0.9436280222, 0.06, 0.06762159439, 0.9323784056, 0.07, 0.07885771977, 0.9211422802, 0.08, 0.09007812584,
            0.9099218742, 0.09, 0.1012805939, 0.8987194061, 0.1, 0.112462916, 0.887537084, 0.11, 0.1236228962, 0.8763771038,
            0.12, 0.1347583518, 0.8652416482, 0.13, 0.1458671148, 0.8541328852, 0.14, 0.1569470331, 0.8430529669, 0.15,
            0.1679959714, 0.8320040286, 0.16, 0.1790118132, 0.8209881868, 0.17, 0.1899924612, 0.8100075388, 0.18, 0.200935839,
            0.799064161, 0.19, 0.2118398922, 0.7881601078, 0.2, 0.2227025892, 0.7772974108, 0.21, 0.233521923, 0.766478077,
            0.22, 0.2442959116, 0.7557040884, 0.23, 0.2550225996, 0.7449774004, 0.24, 0.265700059, 0.734299941, 0.25,
            0.2763263902, 0.7236736098, 0.26, 0.2868997232, 0.7131002768, 0.27, 0.2974182185, 0.7025817814, 0.28, 0.307880068,
            0.692119932, 0.29, 0.3182834959, 0.6817165041, 0.3, 0.3286267595, 0.6713732405, 0.31, 0.3389081503, 0.6610918497,
            0.32, 0.3491259948, 0.6508740052, 0.33, 0.359278655, 0.640721345, 0.34, 0.3693645293, 0.6306354707, 0.35,
            0.3793820536, 0.6206179464, 0.36, 0.3893297011, 0.6106702989, 0.37, 0.399205984, 0.600794016, 0.38, 0.4090094534,
            0.5909905466, 0.39, 0.4187387001, 0.5812612999, 0.4, 0.428392355, 0.5716076449, 0.41, 0.4379690902, 0.5620309098,
            0.42, 0.4474676184, 0.5525323816, 0.43, 0.4568866945, 0.5431133054, 0.44, 0.4662251153, 0.5337748847, 0.45,
            0.4754817198, 0.5245182802, 0.46, 0.48465539, 0.51534461, 0.47, 0.4937450509, 0.5062549491, 0.48, 0.5027496707,
            0.4972503293, 0.49, 0.5116682612, 0.4883317388, 0.5, 0.5204998778, 0.4795001222, 0.51, 0.5292436198, 0.4707563802,
            0.52, 0.5378986305, 0.4621013695, 0.53, 0.5464640969, 0.4535359031, 0.54, 0.5549392505, 0.4450607495, 0.55,
            0.5633233663, 0.4366766337, 0.56, 0.5716157638, 0.4283842362, 0.57, 0.5798158062, 0.4201841938, 0.58, 0.5879229004,
            0.4120770996, 0.59, 0.5959364972, 0.4040635028, 0.6, 0.6038560908, 0.3961439092, 0.61, 0.6116812189, 0.3883187811,
            0.62, 0.6194114619, 0.3805885381, 0.63, 0.6270464433, 0.3729535567, 0.64, 0.6345858291, 0.3654141709, 0.65,
            0.6420293274, 0.3579706726, 0.66, 0.649376688, 0.350623312, 0.67, 0.6566277023, 0.3433722977, 0.68, 0.6637822027,
            0.3362177973, 0.69, 0.6708400622, 0.3291599378, 0.7, 0.6778011938, 0.3221988062, 0.71, 0.6846655502, 0.3153344498,
            0.72, 0.6914331231, 0.3085668769, 0.73, 0.6981039429, 0.3018960571, 0.74, 0.7046780779, 0.2953219222, 0.75,
            0.7111556337, 0.2888443664, 0.76, 0.7175367528, 0.2824632472, 0.77, 0.723821614, 0.276178386, 0.78, 0.7300104313,
            0.2699895687, 0.79, 0.7361034538, 0.2638965462, 0.8, 0.7421009647, 0.2578990353, 0.81, 0.7480032806, 0.2519967194,
            0.82, 0.7538107509, 0.2461892491, 0.83, 0.7595237569, 0.2404762431, 0.84, 0.7651427115, 0.2348572886, 0.85,
            0.7706680576, 0.2293319424, 0.86, 0.7761002683, 0.2238997317, 0.87, 0.7814398455, 0.2185601545, 0.88, 0.7866873192,
            0.2133126808, 0.89, 0.7918432468, 0.2081567532, 0.9, 0.7969082124, 0.2030917876, 0.91, 0.8018828258, 0.1981171742,
            0.92, 0.8067677215, 0.1932322785, 0.93, 0.8115635586, 0.1884364414, 0.94, 0.816271019, 0.183728981, 0.95,
            0.8208908073, 0.1791091927, 0.96, 0.8254236496, 0.1745763504, 0.97, 0.829870293, 0.170129707, 0.98, 0.8342315043,
            0.1657684957, 0.99, 0.8385080696, 0.1614919304, 1, 0.8427007929, 0.1572992071, 1.01, 0.8468104962, 0.1531895038,
            1.02, 0.8508380177, 0.1491619823, 1.03, 0.8547842115, 0.1452157886, 1.04, 0.8586499465, 0.1413500535, 1.05,
            0.8624361061, 0.1375638939, 1.06, 0.8661435866, 0.1338564134, 1.07, 0.8697732972, 0.1302267028, 1.08, 0.8733261584,
            0.1266738416, 1.09, 0.8768031019, 0.1231968981, 1.1, 0.8802050696, 0.1197949304, 1.11, 0.8835330124, 0.1164669876,
            1.12, 0.8867878902, 0.1132121098, 1.13, 0.8899706704, 0.1100293296, 1.14, 0.8930823276, 0.1069176724, 1.15,
            0.8961238429, 0.1038761571, 1.16, 0.8990962029, 0.1009037971, 1.17, 0.902000399, 0.09799960103, 1.18, 0.9048374269,
            0.09516257309, 1.19, 0.907608286, 0.09239171403, 1.2, 0.9103139782, 0.08968602177, 1.21, 0.912955508, 0.08704449203,
            1.22, 0.915533881, 0.08446611897, 1.23, 0.9180501041, 0.08194989587, 1.24, 0.9205051843, 0.0794948157, 1.25,
            0.9229001283, 0.07709987174, 1.26, 0.9252359418, 0.07476405819, 1.27, 0.9275136293, 0.07248637071, 1.28,
            0.929734193, 0.07026580699, 1.29, 0.9318986327, 0.06810136731, 1.3, 0.9340079449, 0.06599205506, 1.31, 0.9360631228,
            0.06393687723, 1.32, 0.9380651551, 0.06193484492, 1.33, 0.9400150262, 0.05998497384, 1.34, 0.9419137153,
            0.05808628474, 1.35, 0.9437621961, 0.05623780388, 1.36, 0.9455614366, 0.05443856344, 1.37, 0.947312398,
            0.05268760197, 1.38, 0.9490160353, 0.05098396474, 1.39, 0.9506732958, 0.0493267042, 1.4, 0.9522851198,
            0.04771488024, 1.41, 0.9538524394, 0.04614756064, 1.42, 0.9553761786, 0.04462382136, 1.43, 0.9568572531,
            0.04314274686, 1.44, 0.9582965696, 0.0417034304, 1.45, 0.9596950256, 0.04030497436, 1.46, 0.9610535095,
            0.03894649049, 1.47, 0.9623728999, 0.03762710015, 1.48, 0.9636540654, 0.03634593459, 1.49, 0.9648978648,
            0.03510213516, 1.5, 0.9661051465, 0.03389485353, 1.51, 0.9672767481, 0.03272325187, 1.52, 0.9684134969,
            0.03158650308, 1.53, 0.9695162091, 0.03048379091, 1.54, 0.9705856899, 0.02941431014, 1.55, 0.9716227333,
            0.02837726674, 1.56, 0.972628122, 0.02737187797, 1.57, 0.9736026275, 0.02639737254, 1.58, 0.9745470093,
            0.02545299066, 1.59, 0.9754620158, 0.02453798418, 1.6, 0.9763483833, 0.02365161666, 1.61, 0.9772068366,
            0.02279316342, 1.62, 0.9780380884, 0.02196191163, 1.63, 0.9788428397, 0.02115716033, 1.64, 0.9796217795,
            0.02037822048, 1.65, 0.980375585, 0.01962441498, 1.66, 0.9811049213, 0.01889507869, 1.67, 0.9818104416,
            0.01818955844, 1.68, 0.982492787, 0.017507213, 1.69, 0.9831525869, 0.01684741311, 1.7, 0.9837904586, 0.01620954141,
            1.71, 0.9844070075, 0.01559299246, 1.72, 0.9850028274, 0.01499717264, 1.73, 0.9855784998, 0.01442150017, 1.74,
            0.986134595, 0.013865405, 1.75, 0.9866716712, 0.01332832878, 1.76, 0.9871902752, 0.01280972477, 1.77, 0.9876909422,
            0.01230905778, 1.78, 0.9881741959, 0.01182580407, 1.79, 0.9886405487, 0.01135945129, 1.8, 0.9890905016,
            0.01090949836, 1.81, 0.9895245446, 0.01047545538, 1.82, 0.9899431565, 0.0100568435, 1.83, 0.9903468051,
            0.009653194867, 1.84, 0.9907359476, 0.009264052447, 1.85, 0.9911110301, 0.008888969944, 1.86, 0.9914724883,
            0.008527511664, 1.87, 0.9918207476, 0.008179252389, 1.88, 0.9921562228, 0.007843777245, 1.89, 0.9924793184,
            0.007520681567, 1.9, 0.9927904292, 0.007209570765, 1.91, 0.9930899398, 0.00691006018, 1.92, 0.9933782251,
            0.006621774943, 1.93, 0.9936556502, 0.00634434983, 1.94, 0.9939225709, 0.006077429111, 1.95, 0.9941793336,
            0.005820666408, 1.96, 0.9944262755, 0.005573724535, 1.97, 0.9946637246, 0.005336275354, 1.98, 0.9948920004,
            0.005107999613, 1.99, 0.9951114132, 0.0048885868, 2, 0.995322265, 0.004677734981, 2.01, 0.9955248494,
            0.004475150645, 2.02, 0.9957194515, 0.004280548548, 2.03, 0.9959063484, 0.004093651556, 2.04, 0.9960858095,
            0.003914190488, 2.05, 0.996258096, 0.003741903956, 2.06, 0.9964234618, 0.00357653821, 2.07, 0.996582153,
            0.003417846983, 2.08, 0.9967344087, 0.00326559133, 2.09, 0.9968804605, 0.003119539476, 2.1, 0.9970205333,
            0.002979466656, 2.11, 0.997154845, 0.002845154969, 2.12, 0.9972836068, 0.002716393215, 2.13, 0.9974070233,
            0.002592976749, 2.14, 0.9975252927, 0.002474707329, 2.15, 0.997638607, 0.002361392963, 2.16, 0.9977471522,
            0.002252847763, 2.17, 0.9978511082, 0.002148891798, 2.18, 0.9979506491, 0.002049350947, 2.19, 0.9980459432,
            0.001954056757, 2.2, 0.9981371537, 0.001862846298, 2.21, 0.998224438, 0.001775562024, 2.22, 0.9983079484,
            0.001692051635, 2.23, 0.9983878321, 0.001612167938, 2.24, 0.9984642313, 0.001535768715, 2.25, 0.9985372834,
            0.001462716587, 2.26, 0.9986071211, 0.001392878884, 2.27, 0.9986738725, 0.001326127516, 2.28, 0.9987376612,
            0.00126233885, 2.29, 0.9987986064, 0.001201393577, 2.3, 0.9988568234, 0.001143176597, 2.31, 0.9989124231,
            0.001087576896, 2.32, 0.9989655126, 0.001034487427, 2.33, 0.999016195, 9.838049935E-4, 2.34, 0.9990645699,
            9.354301389E-4, 2.35, 0.999110733, 8.892670321E-4, 2.36, 0.9991547766, 8.452233592E-4, 2.37, 0.9991967898,
            8.032102164E-4, 2.38, 0.999236858, 7.631420051E-4, 2.39, 0.9992750637, 7.249363296E-4, 2.4, 0.9993114861,
            6.885138967E-4, 2.41, 0.9993462016, 6.537984174E-4, 2.42, 0.9993792835, 6.207165117E-4, 2.43, 0.9994108024,
            5.891976143E-4, 2.44, 0.9994408261, 5.591738836E-4, 2.45, 0.9994694199, 5.305801123E-4, 2.46, 0.9994966464,
            5.033536406E-4, 2.47, 0.9995225657, 4.774342711E-4, 2.48, 0.9995472358, 4.527641863E-4, 2.49, 0.9995707121,
            4.292878677E-4, 2.5, 0.999593048, 4.069520175E-4, 2.51, 0.9996142945, 3.857054817E-4, 2.52, 0.9996345008,
            3.654991765E-4, 2.53, 0.999653714, 3.462860151E-4, 2.54, 0.9996719792, 3.280208377E-4, 2.55, 0.9996893397,
            3.106603426E-4, 2.56, 0.999705837, 2.941630205E-4, 2.57, 0.9997215109, 2.784890889E-4, 2.58, 0.9997363996,
            2.636004298E-4, 2.59, 0.9997505395, 2.494605291E-4, 2.6, 0.9997639656, 2.360344165E-4, 2.61, 0.9997767114,
            2.232886093E-4, 2.62, 0.9997888089, 2.111910558E-4, 2.63, 0.9998002889, 1.997110819E-4, 2.64, 0.9998111807,
            1.888193387E-4, 2.65, 0.9998215122, 1.78487752E-4, 2.66, 0.9998313105, 1.68689473E-4, 2.67, 0.9998406012,
            1.593988312E-4, 2.68, 0.9998494087, 1.50591288E-4, 2.69, 0.9998577566, 1.422433929E-4, 2.7, 0.9998656673,
            1.343327399E-4, 2.71, 0.9998731621, 1.268379266E-4, 2.72, 0.9998802615, 1.197385135E-4, 2.73, 0.999886985,
            1.130149854E-4, 2.74, 0.9998933513, 1.066487141E-4, 2.75, 0.9998993781, 1.006219221E-4, 2.76, 0.9999050824,
            9.491764781E-5, 2.77, 0.9999104803, 8.951971163E-5, 2.78, 0.9999155873, 8.44126837E-5, 2.79, 0.9999204181,
            7.958185251E-5, 2.8, 0.9999249868, 7.501319467E-5, 2.81, 0.9999293067, 7.069334585E-5, 2.82, 0.9999333904,
            6.660957274E-5, 2.83, 0.9999372503, 6.274974606E-5, 2.84, 0.9999408977, 5.910231454E-5, 2.85, 0.9999443437,
            5.565627996E-5, 2.86, 0.9999475988, 5.240117304E-5, 2.87, 0.999950673, 4.932703031E-5, 2.88, 0.9999535756,
            4.642437184E-5, 2.89, 0.9999563158, 4.368417984E-5, 2.9, 0.9999589021, 4.10978781E-5, 2.91, 0.9999613427,
            3.865731224E-5, 2.92, 0.9999636453, 3.635473073E-5, 2.93, 0.9999658172, 3.418276664E-5, 2.94, 0.9999678656,
            3.213442023E-5, 2.95, 0.999969797, 3.020304206E-5, 2.96, 0.9999716177, 2.838231701E-5, 2.97, 0.9999733338,
            2.666624873E-5, 2.98, 0.9999749509, 2.504914491E-5, 2.99, 0.9999764744, 2.352560308E-5, 3, 0.9999779095,
            2.2090497E-5, 3.01, 0.999979261, 2.073896364E-5, 3.02, 0.9999805336, 1.946639071E-5, 3.03, 0.9999817316,
            1.826840475E-5, 3.04, 0.9999828591, 1.714085965E-5, 3.05, 0.9999839202, 1.607982576E-5, 3.06, 0.9999849184,
            1.50815794E-5, 3.07, 0.9999858574, 1.414259288E-5, 3.08, 0.9999867405, 1.325952494E-5, 3.09, 0.9999875708,
            1.242921158E-5, 3.1, 0.9999883513, 1.164865737E-5, 3.11, 0.999989085, 1.091502706E-5, 3.12, 0.9999897744,
            1.022563767E-5, 3.13, 0.999990422, 9.577950825E-6, 3.14, 0.9999910304, 8.969565553E-6, 3.15, 0.9999916018,
            8.398211315E-6, 3.16, 0.9999921383, 7.861741419E-6, 3.17, 0.9999926419, 7.358126714E-6, 3.18, 0.9999931146,
            6.885449582E-6, 3.19, 0.9999935581, 6.441898214E-6, 3.2, 0.9999939742, 6.025761152E-6, 3.21, 0.9999943646,
            5.635422091E-6, 3.22, 0.9999947306, 5.269354929E-6, 3.23, 0.9999950739, 4.926119054E-6, 3.24, 0.9999953956,
            4.604354858E-6, 3.25, 0.9999956972, 4.302779464E-6, 3.26, 0.9999959798, 4.020182668E-6, 3.27, 0.9999962446,
            3.755423075E-6, 3.28, 0.9999964926, 3.507424424E-6, 3.29, 0.9999967248, 3.275172096E-6, 3.3, 0.9999969423,
            3.057709796E-6, 3.31, 0.9999971459, 2.854136403E-6, 3.32, 0.9999973364, 2.663602966E-6, 3.33, 0.9999975147,
            2.485309869E-6, 3.34, 0.9999976815, 2.318504126E-6, 3.35, 0.9999978375, 2.16247682E-6, 3.36, 0.9999979834,
            2.016560669E-6, 3.37, 0.9999981199, 1.880127722E-6, 3.38, 0.9999982474, 1.752587167E-6, 3.39, 0.9999983666,
            1.633383262E-6, 3.4, 0.999998478, 1.521993363E-6, 3.41, 0.9999985821, 1.417926066E-6, 3.42, 0.9999986793,
            1.320719436E-6, 3.43, 0.9999987701, 1.22993934E-6, 3.44, 0.9999988548, 1.145177859E-6, 3.45, 0.9999989339,
            1.066051794E-6, 3.46, 0.9999990078, 9.922012405E-7, 3.47, 0.9999990767, 9.232882536E-7, 3.48, 0.999999141,
            8.589955721E-7, 3.49, 0.999999201, 7.990254205E-7, 3.5, 0.9999992569, 7.430983723E-7, 3.51, 0.999999309,
            6.909522775E-7, 3.52, 0.9999993577, 6.423412472E-7, 3.53, 0.999999403, 5.97034696E-7, 3.54, 0.9999994452,
            5.548164366E-7, 3.55, 0.9999994845, 5.154838246E-7, 3.56, 0.9999995212, 4.788469521E-7, 3.57, 0.9999995553,
            4.447278856E-7, 3.58, 0.999999587, 4.129599471E-7, 3.59, 0.9999996166, 3.833870369E-7, 3.6, 0.9999996441,
            3.55862993E-7, 3.61, 0.9999996697, 3.302509891E-7, 3.62, 0.9999996936, 3.064229655E-7, 3.63, 0.9999997157,
            2.84259094E-7, 3.64, 0.9999997364, 2.636472726E-7, 3.65, 0.9999997555, 2.444826506E-7, 3.66, 0.9999997733,
            2.266671803E-7, 3.67, 0.9999997899, 2.101091961E-7, 3.68, 0.9999998053, 1.947230172E-7, 3.69, 0.9999998196,
            1.804285741E-7, 3.7, 0.9999998328, 1.671510579E-7, 3.71, 0.9999998452, 1.548205892E-7, 3.72, 0.9999998566,
            1.433719078E-7, 3.73, 0.9999998673, 1.327440803E-7, 3.74, 0.9999998771, 1.228802254E-7, 3.75, 0.9999998863,
            1.137272566E-7, 3.76, 0.9999998948, 1.052356387E-7, 3.77, 0.9999999026, 9.735916113E-8, 3.78, 0.9999999099,
            9.005472354E-8, 3.79, 0.9999999167, 8.328213537E-8, 3.8, 0.999999923, 7.700392746E-8, 3.81, 0.9999999288,
            7.118517524E-8, 3.82, 0.9999999342, 6.579333302E-8, 3.83, 0.9999999392, 6.07980784E-8, 3.84, 0.9999999438,
            5.617116653E-8, 3.85, 0.9999999481, 5.188629341E-8, 3.86, 0.9999999521, 4.791896777E-8, 3.87, 0.9999999558,
            4.424639108E-8, 3.88, 0.9999999592, 4.084734509E-8, 3.89, 0.9999999623, 3.770208654E-8, 3.9, 0.9999999652,
            3.47922486E-8, 3.91, 0.9999999679, 3.210074851E-8, 3.92, 0.9999999704, 2.961170129E-8, 3.93, 0.9999999727,
            2.731033888E-8, 3.94, 0.9999999748, 2.518293455E-8, 3.95, 0.9999999768, 2.321673224E-8, 3.96, 0.9999999786,
            2.139988041E-8, 3.97, 0.9999999803, 1.97213703E-8, 3.98, 0.9999999818, 1.817097817E-8, 3.99, 0.9999999833,
            1.673921137E-8, 4, 0.9999999846, 1.54172579E-8, 4.01, 0.9999999858, 1.419693942E-8, 4.02, 0.9999999869,
            1.307066726E-8, 4.03, 0.999999988, 1.203140141E-8, 4.04, 0.9999999889, 1.107261227E-8, 4.05, 0.9999999898,
            1.018824493E-8, 4.06, 0.9999999906, 9.372685852E-9, 4.07, 0.9999999914, 8.6207318E-9, 4.08, 0.9999999921,
            7.9275609E-9, 4.09, 0.9999999927, 7.288705628E-9, 4.1, 0.9999999933, 6.700027654E-9, 4.11, 0.9999999938,
            6.157694403E-9, 4.12, 0.9999999943, 5.658157219E-9, 4.13, 0.9999999948, 5.198131035E-9, 4.14, 0.9999999952,
            4.774575438E-9, 4.15, 0.9999999956, 4.384677048E-9, 4.16, 0.999999996, 4.025833121E-9, 4.17, 0.9999999963,
            3.695636289E-9, 4.18, 0.9999999966, 3.391860369E-9, 4.19, 0.9999999969, 3.112447161E-9, 4.2, 0.9999999971,
            2.85549418E-9, 4.21, 0.9999999974, 2.619243247E-9, 4.22, 0.9999999976, 2.402069889E-9, 4.23, 0.9999999978,
            2.202473489E-9, 4.24, 0.999999998, 2.019068139E-9, 4.25, 0.9999999981, 1.850574137E-9, 4.26, 0.9999999983,
            1.695810108E-9, 4.27, 0.9999999984, 1.553685672E-9, 4.28, 0.9999999986, 1.423194653E-9, 4.29, 0.9999999987,
            1.303408771E-9, 4.3, 0.9999999988, 1.193471794E-9, 4.31, 0.9999999989, 1.092594113E-9, 4.32, 0.999999999,
            1.000047714E-9, 4.33, 0.9999999991, 9.151615109E-10, 4.34, 0.9999999992, 8.373170289E-10, 4.35, 0.9999999992,
            7.659443988E-10, 4.36, 0.9999999993, 7.005186488E-10, 4.37, 0.9999999994, 6.405562688E-10, 4.38, 0.9999999994,
            5.856120305E-10, 4.39, 0.9999999995, 5.352760428E-10, 4.4, 0.9999999995, 4.891710271E-10, 4.41, 0.9999999996,
            4.469497955E-10, 4.42, 0.9999999996, 4.082929188E-10, 4.43, 0.9999999996, 3.729065688E-10, 4.44, 0.9999999997,
            3.405205234E-10, 4.45, 0.9999999997, 3.108863231E-10, 4.46, 0.9999999997, 2.837755659E-10, 4.47, 0.9999999997,
            2.589783327E-10, 4.48, 0.9999999998, 2.363017324E-10, 4.49, 0.9999999998, 2.155685576E-10, 4.5, 0.9999999998,
            1.966160442E-10, 4.51, 0.9999999998, 1.792947251E-10, 4.52, 0.9999999998, 1.634673734E-10, 4.53, 0.9999999999,
            1.490080262E-10, 4.54, 0.9999999999, 1.358010845E-10, 4.55, 0.9999999999, 1.237404827E-10, 4.56, 0.9999999999,
            1.127289229E-10, 4.57, 0.9999999999, 1.026771691E-10, 4.58, 0.9999999999, 9.350339611E-11, 4.59, 0.9999999999,
            8.513259009E-11, 4.6, 0.9999999999, 7.749599597E-11, 4.61, 0.9999999999, 7.053060848E-11, 4.62, 0.9999999999,
            6.417870342E-11, 4.63, 0.9999999999, 5.838740617E-11, 4.64, 0.9999999999, 5.310829439E-11, 4.65, 1, 4.829703245E-11,
            4.66, 1, 4.391303505E-11, 4.67, 1, 3.991915777E-11, 4.68, 1, 3.628141247E-11, 4.69, 1, 3.296870565E-11, 4.7, 1,
            2.995259786E-11, 4.71, 1, 2.720708263E-11, 4.72, 1, 2.470838331E-11, 4.73, 1, 2.243476644E-11, 4.74, 1,
            2.036637035E-11, 4.75, 1, 1.848504772E-11, 4.76, 1, 1.677422114E-11, 4.77, 1, 1.52187504E-11, 4.78, 1,
            1.380481085E-11, 4.79, 1, 1.25197817E-11, 4.8, 1, 1.135214359E-11, 4.81, 1, 1.029138468E-11, 4.82, 1,
            9.327914549E-12, 4.83, 1, 8.452985242E-12, 4.84, 1, 7.658618933E-12, 4.85, 1, 6.937541655E-12, 4.86, 1,
            6.283122582E-12, 4.87, 1, 5.689318433E-12, 4.88, 1, 5.150622557E-12, 4.89, 1, 4.662018336E-12, 4.9, 1,
            4.218936524E-12, 4.91, 1, 3.817216229E-12, 4.92, 1, 3.453069205E-12, 4.93, 1, 3.123047199E-12, 4.94, 1,
            2.824012092E-12, 4.95, 1, 2.553108603E-12, 4.96, 1, 2.307739339E-12, 4.97, 1, 2.085541993E-12, 4.98, 1,
            1.88436852E-12, 4.99, 1, 1.702266105E-12, 5, 1, 1.537459794E-12, 5.01, 1, 1.388336629E-12, 5.02, 1, 1.253431168E-12,
            5.03, 1, 1.131412272E-12, 5.04, 1, 1.021071049E-12, 5.05, 1, 9.213098542E-13, 5.06, 1, 8.311322633E-13, 5.07, 1,
            7.496339239E-13, 5.08, 1, 6.759942201E-13, 5.09, 1, 6.094686739E-13, 5.1, 1, 5.493820218E-13, 5.11, 1,
            4.95121907E-13, 5.12, 1, 4.461331351E-13, 5.13, 1, 4.019124427E-13, 5.14, 1, 3.620037349E-13, 5.15, 1,
            3.259937501E-13, 5.16, 1, 2.935081158E-13, 5.17, 1, 2.64207759E-13, 5.18, 1, 2.377856423E-13, 5.19, 1,
            2.139637943E-13, 5.2, 1, 1.92490611E-13, 5.21, 1, 1.731384014E-13, 5.22, 1, 1.557011581E-13, 5.23, 1,
            1.399925307E-13, 5.24, 1, 1.258439852E-13, 5.25, 1, 1.131031327E-13, 5.26, 1, 1.016322106E-13, 5.27, 1,
            9.130670535E-14, 5.28, 1, 8.201410124E-14, 5.29, 1, 7.365274547E-14, 5.3, 1, 6.61308185E-14, 5.31, 1,
            5.936540004E-14, 5.32, 1, 5.328162203E-14, 5.33, 1, 4.781190076E-14, 5.34, 1, 4.289524077E-14, 5.35, 1,
            3.847660405E-14, 5.36, 1, 3.450633843E-14, 5.37, 1, 3.093965988E-14, 5.38, 1, 2.773618355E-14, 5.39, 1,
            2.485949919E-14, 5.4, 1, 2.22767868E-14, 5.41, 1, 1.99584687E-14, 5.42, 1, 1.787789481E-14, 5.43, 1,
            1.601105778E-14, 5.44, 1, 1.433633544E-14, 5.45, 1, 1.283425787E-14, 5.46, 1, 1.148729678E-14, 5.47, 1,
            1.027967515E-14, 5.48, 1, 9.197195206E-15, 5.49, 1, 8.227082982E-15, 5.5, 1, 7.357847918E-15, 5.51, 1,
            6.579156048E-15, 5.52, 1, 5.881715512E-15, 5.53, 1, 5.257173189E-15, 5.54, 1, 4.698021418E-15, 5.55, 1,
            4.197513817E-15, 5.56, 1, 3.749589341E-15, 5.57, 1, 3.348803794E-15, 5.58, 1, 2.990268055E-15, 5.59, 1,
            2.669592396E-15, 5.6, 1, 2.382836285E-15, 5.61, 1, 2.126463152E-15, 5.62, 1, 1.897299633E-15, 5.63, 1,
            1.692498858E-15, 5.64, 1, 1.509507386E-15, 5.65, 1, 1.346035433E-15, 5.66, 1, 1.200030075E-15, 5.67, 1,
            1.069651126E-15, 5.68, 1, 9.532494321E-16, 5.69, 1, 8.493473451E-16, 5.7, 1, 7.566211622E-16, 5.71, 1,
            6.738853306E-16, 5.72, 1, 6.00078249E-16, 5.73, 1, 5.342495045E-16, 5.74, 1, 4.755484026E-16, 5.75, 1,
            4.232136617E-16, 5.76, 1, 3.765641562E-16, 5.77, 1, 3.34990602E-16, 5.78, 1, 2.979480921E-16, 5.79, 1,
            2.649493944E-16, 5.8, 1, 2.355589375E-16, 5.81, 1, 2.093874133E-16, 5.82, 1, 1.860869348E-16, 5.83, 1,
            1.653466935E-16, 5.84, 1, 1.468890646E-16, 5.85, 1, 1.30466115E-16, 5.86, 1, 1.158564737E-16, 5.87, 1,
            1.028625268E-16, 5.88, 1, 9.130790417E-17, 5.89, 1, 8.10352283E-17, 5.9, 1, 7.190409784E-17, 5.91, 1,
            6.378928229E-17, 5.92, 1, 5.657910571E-17, 5.93, 1, 5.017400017E-17, 5.94, 1, 4.44852112E-17, 5.95, 1,
            3.943363977E-17, 5.96, 1, 3.494880636E-17, 5.97, 1, 3.096792461E-17, 5.98, 1, 2.743507314E-17, 5.99, 1,
            2.430045513E-17};

    /** */
    private ErfPrecision()
    {
        // Utility class
    }

    /**
     * Test the precision of the erf() inplementations.
     * @param erfFunction DoubleFunction; the function to use for erf
     */
    private static void erfTest(final DoubleFunction<Double> erfFunction)
    {
        System.out.println("v\treal erf(v)\tcalc erf(v)\treal-calc\t(r-c)/r");
        for (int i = 0; i < TESTDATA.length / 3; i++)
        {
            double expected = TESTDATA[3 * i + 1];
            double calculated = erfFunction.apply(TESTDATA[3 * i]);
            double fraction = expected == 0 ? 0.0 : (expected - calculated) / expected;
            System.out.println(
                    TESTDATA[3 * i] + "\t" + expected + "\t" + calculated + "\t" + (expected - calculated) + "\t" + fraction);
        }
    }

    /**
     * Test the precision of the erf() inplementations.
     * @param erfInvFunction DoubleFunction; the function to use for erf
     */
    private static void erfInvTest(final DoubleFunction<Double> erfInvFunction)
    {
        System.out.println("v\treal erf-1(v)\tcalc erf-1(v)\treal-calc\t(r-c)/r");
        for (int i = 0; i < TESTDATA.length / 3; i++)
        {
            double expected = TESTDATA[3 * i];
            double calculated = erfInvFunction.apply(TESTDATA[3 * i + 1]);
            double fraction = expected == 0 ? 0.0 : (expected - calculated) / expected;
            if (!Double.isInfinite(calculated))
            {
                System.out.println(TESTDATA[3 * i + 1] + "\t" + expected + "\t" + calculated + "\t" + (expected - calculated)
                        + "\t" + fraction);
            }
        }
    }

    /**
     * Calculate erf(z), where three different algorithms are used for small, medium, and large values.
     * @param z double; the value to calculate erf(z) for
     * @return double; erf(z)
     */
    static double erf(final double z)
    {
        double zpos = Math.abs(z);
        if (zpos < 0.5)
        {
            return erfSmall(z);
        }
        if (zpos > 3.8)
        {
            return erfBig(z);
        }
        return erfTaylor(z);
    }

    /**
     * The Taylor series for erf(z) for abs(z) <u>&lt;</u> 0.5 that is used is:<br>
     * &nbsp; &nbsp; erf(z) = (exp(-z<sup>2</sup>) / &radic;&pi;) &Sigma; [ 2z<sup>2n + 1</sup> / (2n + 1)!!]<br>
     * where the !! operator is the 'double factorial' operator which is (n).(n-2)...8.4.2 for even n, and (n).(n-2)...3.5.1 for
     * odd n. See <a href="https://mathworld.wolfram.com/Erf.html">https://mathworld.wolfram.com/Erf.html</a> formula (9) and
     * (10). This function would work well for z <u>&lt;</u> 0.5.
     * @param z double; the parameter
     * @return double; erf(x)
     */
    static double erfSmall(final double z)
    {
        double zpos = Math.abs(z);
        // @formatter:off
        double sum = zpos 
                + 2.0 * Math.pow(zpos, 3) / 3.0 
                + 4.0 * Math.pow(zpos, 5) / 15.0 
                + 8.0 * Math.pow(zpos, 7) / 105.0
                + 16.0 * Math.pow(zpos, 9) / 945.0 
                + 32.0 * Math.pow(zpos, 11) / 10395.0
                + 64.0 * Math.pow(zpos, 13) / 135135.0 
                + 128.0 * Math.pow(zpos, 15) / 2027025.0
                + 256.0 * Math.pow(zpos, 17) / 34459425.0 
                + 512.0 * Math.pow(zpos, 19) / 654729075.0
                + 1024.0 * Math.pow(zpos, 21) / 13749310575.0;
        // @formatter:on
        return Math.signum(z) * sum * 2.0 * Math.exp(-zpos * zpos) / Math.sqrt(Math.PI);
    }

    /**
     * Calculate erf(z) for large values using the Taylor series:<br>
     * &nbsp; &nbsp; erf(z) = 1 - (exp(-z<sup>2</sup>) / &radic;&pi;) &Sigma; [ (-1)<sup>n</sup> (2n - 1)!! z<sup>-(2n +
     * 1)</sup> / 2<sup>n</sup>]<br>
     * where the !! operator is the 'double factorial' operator which is (n).(n-2)...8.4.2 for even n, and (n).(n-2)...3.5.1 for
     * odd n. See <a href="https://mathworld.wolfram.com/Erf.html">https://mathworld.wolfram.com/Erf.html</a> formula (18) to
     * (20). This function would work well for z <u>&gt;</u> 3.7.
     * @param z double; the argument
     * @return double; erf(z)
     */
    static double erfBig(final double z)
    {
        double zpos = Math.abs(z);
        // @formatter:off
        double sum = 1.0 / zpos 
                - (1.0 / 2.0) * Math.pow(zpos, -3) 
                + (3.0 / 4.0) * Math.pow(zpos, -5)
                - (15.0 / 8.0) * Math.pow(zpos, -7) 
                + (105.0 / 16.0) * Math.pow(zpos, -9) 
                - (945.0 / 32.0) * Math.pow(zpos, -11)
                + (10395.0 / 64.0) * Math.pow(zpos, -13) 
                - (135135.0 / 128.0) * Math.pow(zpos, -15)
                + (2027025.0 / 256.0) * Math.pow(zpos, -17);
        // @formatter:on
        return Math.signum(z) * (1.0 - sum * Math.exp(-zpos * zpos) / Math.sqrt(Math.PI));
    }

    /**
     * Calculate erf(z) using the Taylor series:<br>
     * &nbsp; &nbsp; erf(z) = (2/&radic;&pi;) (z - z<sup>3</sup>/3 + z<sup>5</sup>/10 - z<sup>7</sup>/42 + z<sup>9</sup>/216 -
     * ...)<br>
     * The factors are given by <a href="https://oeis.org/A007680">https://oeis.org/A007680</a>, which evaluates to a(n) =
     * (2n+1)n!. See <a href="https://en.wikipedia.org/wiki/Error_function">https://en.wikipedia.org/wiki/Error_function</a>.
     * This works pretty well on the interval [0.5,3.7].
     * @param z double; the argument
     * @return double; erf(z)
     */
    static double erfTaylor(final double z)
    {
        double zpos = Math.abs(z);
        double d = zpos;
        double zpow = zpos;
        for (int i = 1; i < 64; i++) // max 64 steps
        {
            // calculate Math.pow(zpos, 2 * i + 1) / ((2 * i + 1) * factorial(i));
            zpow *= zpos * zpos;
            double term = zpow / ((2.0 * i + 1.0) * ProbMath.factorial(i));
            d += term * ((i & 1) == 0 ? 1 : -1);
            if (term < 1E-16)
            {
                break;
            }
        }
        return Math.signum(z) * d * 2.0 / Math.sqrt(Math.PI);
    }

    /** First 17 terms of A002067, see <a href="https://oeis.org/A002067">https://oeis.org/A002067</a>. */
    private static final double[] A002067 =
            new double[] {1.0, 1.0, 7.0, 127.0, 4369.0, 243649.0, 20036983.0, 2280356863.0, 343141433761.0, 65967241200001.0,
                    15773461423793767.0, 4591227123230945407.0, 1598351733247609852849.0, 655782249799531714375489.0,
                    313160404864973852338669783.0, 172201668512657346455126457343.0, 108026349476762041127839800617281.0};

    /** First 17 terms of A122551, see <a href="https://oeis.org/A007019">https://oeis.org/A007019</a>. */
    private static final double[] A122551 = new double[] {2.0, 24.0, 960.0, 80640.0, 11612160.0, 2554675200.0, 797058662400.0,
            334764638208000.0, 182111963185152000.0, 124564582818643968000.0, 104634249567660933120000.0,
            105889860562472864317440000.0, 127067832674967437180928000000.0, 178403237075654281802022912000000.0,
            289726857010862553646485209088000000.0, 538891954040204349782462488903680000000.0,
            1138139806932911586740560776564572160000000.0};

    /**
     * Approximates erf<sup>-1</sup>(p) using a Taylor series.<br>
     * The Taylor series for erf<sup>-1</sup>(z) is:<br>
     * &nbsp; &nbsp; erf<sup>-1</sup>(p) = &radic;&pi; &Sigma; [ 1/2 p + 1/24 &pi; p<sup>3</sup> + 7/960 &pi;<sup>2</sup>
     * p<sup>5</sup> + 127/80640 &pi;<sup>3</sup> p<sup>7</sup> + ... ]<br>
     * See <a href="https://mathworld.wolfram.com/InverseErf.html">https://mathworld.wolfram.com/InverseErf.html</a>. <br>
     * The factors are given by <a href="https://oeis.org/A002067">https://oeis.org/A002067</a> which evaluates to and
     * <a href="https://oeis.org/A122551">https://oeis.org/A122551</a>
     * @param y double; the cumulative probability to calculate the inverse error function for
     * @return erf<sup>-1</sup>(y)
     */
    static double erfInv(final double y)
    {
        double p = Math.abs(y);
        if (p < 0.9)
        {
            double ppow = p;
            double pipow = 1.0;
            double sum = 0.5 * p;
            for (int i = 1; i < 16; i++)
            {
                ppow *= p * p;
                pipow *= Math.PI;
                double term = pipow * ppow * A002067[i] / A122551[i];
                sum += term;
                if (term < 1E-16)
                {
                    break;
                }
            }
            return Math.signum(y) * Math.sqrt(Math.PI) * sum;
        }
        if (p < 1)
        {
            return Math.signum(y) * Math.sqrt(-Math.log(1.0 - p));
        }
        return p < 0 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        // double pi = Math.PI;
        // double a = 0.147;
        // double ln1p2 = Math.log(1 - p * p);
        // double term = (2.0 / (pi * a) + 0.5 * ln1p2);
        // return Math.sqrt(Math.sqrt(term * term - (ln1p2 / a)) - term);
    }

    /**
     * Calculate based on http://www.naic.edu/~jeffh/inverse_cerf.c code.
     * @param y double; value to calculate erf-1(y) for
     * @return double; erf-1(x)
     */
    static double inverseErf(final double y)
    {
        double ax, t, ret;
        ax = Math.abs(y);

        /*
         * This approximation, taken from Table 10 of Blair et al., is valid for |x|<=0.75 and has a maximum relative error of
         * 4.47 x 10^-8.
         */
        if (ax <= 0.75)
        {

            double[] p = new double[] {-13.0959967422, 26.785225760, -9.289057635};
            double[] q = new double[] {-12.0749426297, 30.960614529, -17.149977991, 1.00000000};

            t = y * y - 0.75 * 0.75;
            ret = y * (p[0] + t * (p[1] + t * p[2])) / (q[0] + t * (q[1] + t * (q[2] + t * q[3])));

        }
        else if (ax >= 0.75 && ax <= 0.9375)
        {
            double[] p = new double[] {-.12402565221, 1.0688059574, -1.9594556078, .4230581357};
            double[] q = new double[] {-.08827697997, .8900743359, -2.1757031196, 1.0000000000};

            /*
             * This approximation, taken from Table 29 of Blair et al., is valid for .75<=|x|<=.9375 and has a maximum relative
             * error of 4.17 x 10^-8.
             */
            t = y * y - 0.9375 * 0.9375;
            ret = y * (p[0] + t * (p[1] + t * (p[2] + t * p[3]))) / (q[0] + t * (q[1] + t * (q[2] + t * q[3])));

        }
        else if (ax >= 0.9375 && ax <= (1.0 - 1.0e-9))
        {
            double[] p =
                    new double[] {.1550470003116, 1.382719649631, .690969348887, -1.128081391617, .680544246825, -.16444156791};
            double[] q = new double[] {.155024849822, 1.385228141995, 1.000000000000};

            /*
             * This approximation, taken from Table 50 of Blair et al., is valid for .9375<=|x|<=1-10^-100 and has a maximum
             * relative error of 2.45 x 10^-8.
             */
            t = 1.0 / Math.sqrt(-Math.log(1.0 - ax));
            ret = (p[0] / t + p[1] + t * (p[2] + t * (p[3] + t * (p[4] + t * p[5])))) / (q[0] + t * (q[1] + t * (q[2])));
        }
        else
        {
            ret = Double.POSITIVE_INFINITY;
        }

        return Math.signum(y) * ret;
    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        erfTest(new DoubleFunction<Double>()
        {
            @Override
            public Double apply(final double x)
            {
                return erf(x);
            }
        });

        System.out.println("\n\n");

        erfInvTest(new DoubleFunction<Double>()
        {
            @Override
            public Double apply(final double x)
            {
                return inverseErf(x);
            }
        });
    }

}
