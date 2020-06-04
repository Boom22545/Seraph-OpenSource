package dooger.mods.statgrab.doogerapi.utils;

public enum ModConfigNames {

    APIKEY("ApiKey"), FIRSTTIMEUSINGMOD("FirstTime"), QUEUESTATS("QueueStats"), QUICKQUEUESTATS("QuickQueueStats"), STATDECIMALS("StatDecimals"), VERSION("Version"),
    AUTOLEAVE("AutoLeave"), AUTOLEAVEWINSTREAK("AutoLeaveWinstreak"), AUTOLEAVESTAR("AutoLeaveStar"), QUEUESTATSTIMER("QueueTimer"),
    PARTYMEMBERS("PartyMembers"), SHOWRANKS("ShowRanks"), HudGui("HUDGui"), AVOIDLIST("AvoidList"), PARTYSTATSINVITE("PartyStatsInvite"),
    DUELSCOMPACTSTATS("Duels_Compact"), NICKNAME("NickName"), NICKED("Nicked"), FINALKILLSCOUNTER("FinalKillsCounter"), BEDWARSCOLOURS("BedwarsColours"),
    PARTYMEMBERNOTFOUND("PartyMemberNotFound"), BEDWARSQUEUESTATS("BedwarsQueueStats"), DUELS_ENABLED("Duels"), BEDWARS_ENABLED("Bedwars");

    private String name;

    ModConfigNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
