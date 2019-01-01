package com.gzfd;

import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.interfaces.schedule.BaseCronJob;

public class KqJob extends BaseCronJob {
	KqUtil KU = new KqUtil();

	public void execute() {
		String curTime = TimeUtil.getOnlyCurrentTimeString();
		int curHour = Util.getIntValue(curTime.substring(0, 2), -1);
		int curMinu = Util.getIntValue(curTime.substring(3, 5), -1);
		int syc_kq = Util.getIntValue(KU.getPropValue("KQ_yy_syc", "syc_DK"), 5);
		if ((syc_kq == curHour) && (curMinu == 5)) {
			KU.SyncDaKa("");
		}
		if ((curHour == 0) && (curMinu == 0)) {
			KqUtil.setNjJzGq(false);
		}
		if ((curHour == 1) && (curMinu == 5)) {
			KqUtil.initPubholiDay("");
			KqUtil.initMap("");
			KqUtil.Caljb();
		}
		if ((curHour >= 7) && (curHour <= 22)) {
			KU.setNolcid();
		}
	}
}
