function ListMove(num){
	switch (num){
		case 1:	GetId("Page").style.color="rgba(255,255,255,1)";
				GetId("ForUs").style.color="rgba(255,255,255,.8)";
				GetId("Mine").style.color="rgba(255,255,255,.6)";
				GetId("SingList").style.color="rgba(255,255,255,.4)";
				GetId("MoodList").style.color="rgba(255,255,255,.2)";
				GetId("PanelMine").style.opacity="0";
				GetId("PanelPage").style.opacity="1";
				GetId("PanelForUs").style.opacity="0";
				GetId("PanelSingList").style.opacity="0";
				GetId("PanelMoodList").style.opacity="0";
				GetId("PanelMine").style.zIndex="-1";
				GetId("PanelPage").style.zIndex="10";
				GetId("PanelForUs").style.zIndex="-1";
				GetId("PanelSingList").style.zIndex="-1";
				GetId("PanelMoodList").style.zIndex="-1";
			break;
		case 2:	GetId("Page").style.color="rgba(255,255,255,.8)";
				GetId("ForUs").style.color="rgba(255,255,255,1)";
				GetId("Mine").style.color="rgba(255,255,255,.8)";
				GetId("SingList").style.color="rgba(255,255,255,.6)";
				GetId("MoodList").style.color="rgba(255,255,255,.4)";
				GetId("PanelMine").style.opacity="0";
				GetId("PanelPage").style.opacity="0";
				GetId("PanelForUs").style.opacity="1";
				GetId("PanelSingList").style.opacity="0";
				GetId("PanelMoodList").style.opacity="0";
				GetId("PanelMine").style.zIndex="-1";
				GetId("PanelPage").style.zIndex="-1";
				GetId("PanelForUs").style.zIndex="10";
				GetId("PanelSingList").style.zIndex="-1";
				GetId("PanelMoodList").style.zIndex="-1";
			break;
		case 3:	GetId("Page").style.color="rgba(255,255,255,.6)";
				GetId("ForUs").style.color="rgba(255,255,255,.8)";
				GetId("Mine").style.color="rgba(255,255,255,1)";
				GetId("SingList").style.color="rgba(255,255,255,.8)";
				GetId("MoodList").style.color="rgba(255,255,255,.6)";
				GetId("PanelMine").style.opacity="1";
				GetId("PanelPage").style.opacity="0";
				GetId("PanelForUs").style.opacity="0";
				GetId("PanelSingList").style.opacity="0";
				GetId("PanelMoodList").style.opacity="0";
				GetId("PanelMine").style.zIndex="10";
				GetId("PanelPage").style.zIndex="-1";
				GetId("PanelForUs").style.zIndex="-1";
				GetId("PanelSingList").style.zIndex="-1";
				GetId("PanelMoodList").style.zIndex="-1";
			break;
		case 4:	GetId("Page").style.color="rgba(255,255,255,.4)";
				GetId("ForUs").style.color="rgba(255,255,255,.6)";
				GetId("Mine").style.color="rgba(255,255,255,.8)";
				GetId("SingList").style.color="rgba(255,255,255,1)";
				GetId("MoodList").style.color="rgba(255,255,255,.8)";
				GetId("PanelMine").style.opacity="0";
				GetId("PanelPage").style.opacity="0";
				GetId("PanelForUs").style.opacity="0";
				GetId("PanelSingList").style.opacity="1";
				GetId("PanelMoodList").style.opacity="0";
				GetId("PanelMine").style.zIndex="-1";
				GetId("PanelPage").style.zIndex="-1";
				GetId("PanelForUs").style.zIndex="-1";
				GetId("PanelSingList").style.zIndex="10";
				GetId("PanelMoodList").style.zIndex="-1";
			break;
		default:GetId("Page").style.color="rgba(255,255,255,.2)";
				GetId("ForUs").style.color="rgba(255,255,255,.4)";
				GetId("Mine").style.color="rgba(255,255,255,.6)";
				GetId("SingList").style.color="rgba(255,255,255,.8)";
				GetId("MoodList").style.color="rgba(255,255,255,1)";
				GetId("PanelMine").style.opacity="0";
				GetId("PanelPage").style.opacity="0";
				GetId("PanelForUs").style.opacity="0";
				GetId("PanelSingList").style.opacity="0";
				GetId("PanelMoodList").style.opacity="1";
				GetId("PanelMine").style.zIndex="-1";
				GetId("PanelPage").style.zIndex="-1";
				GetId("PanelForUs").style.zIndex="-1";
				GetId("PanelSingList").style.zIndex="-1";
				GetId("PanelMoodList").style.zIndex="10";
			break;
	}
}
function ColorMine(num){
	switch (num){
		case 1:	GetId("Rencently").style.color="rgba(255,255,255,1)";
				GetId("Like").style.color="rgba(255,255,255,.8)";
				GetId("MoodDairy").style.color="rgba(255,255,255,.6)";
				GetId("MyList").style.color="rgba(255,255,255,.4)";
				GetId("CollectionSongs").style.color="rgba(255,255,255,.2)";
			break;
		case 2:	GetId("Rencently").style.color="rgba(255,255,255,.8)";
				GetId("Like").style.color="rgba(255,255,255,1)";
				GetId("MoodDairy").style.color="rgba(255,255,255,.8)";
				GetId("MyList").style.color="rgba(255,255,255,.6)";
				GetId("CollectionSongs").style.color="rgba(255,255,255,.4)";
			break;
		case 3:	GetId("Rencently").style.color="rgba(255,255,255,.6)";
				GetId("Like").style.color="rgba(255,255,255,.8)";
				GetId("MoodDairy").style.color="rgba(255,255,255,1)";
				GetId("MyList").style.color="rgba(255,255,255,.8)";
				GetId("CollectionSongs").style.color="rgba(255,255,255,.6)";
			break;
		case 4:	GetId("Rencently").style.color="rgba(255,255,255,.4)";
				GetId("Like").style.color="rgba(255,255,255,.6)";
				GetId("MoodDairy").style.color="rgba(255,255,255,.8)";
				GetId("MyList").style.color="rgba(255,255,255,1)";
				GetId("CollectionSongs").style.color="rgba(255,255,255,.8)";
			break;
		default:GetId("Rencently").style.color="rgba(255,255,255,.2)";
				GetId("Like").style.color="rgba(255,255,255,.4)";
				GetId("MoodDairy").style.color="rgba(255,255,255,.6)";
				GetId("MyList").style.color="rgba(255,255,255,.8)";
				GetId("CollectionSongs").style.color="rgba(255,255,255,1)";
			break;
	}
}

function ColorPage(num){
	switch (num){
		case 1:	GetId("Also").style.color="rgba(255,255,255,1)";
				GetId("Selfless").style.color="rgba(255,255,255,.8)";
				GetId("Recommand").style.color="rgba(255,255,255,.6)";
				GetId("GuessLike").style.color="rgba(255,255,255,.4)";
				GetId("MusicJournal").style.color="rgba(255,255,255,.2)";
			break;
		case 2:	GetId("Also").style.color="rgba(255,255,255,.8)";
				GetId("Selfless").style.color="rgba(255,255,255,1)";
				GetId("Recommand").style.color="rgba(255,255,255,.8)";
				GetId("GuessLike").style.color="rgba(255,255,255,.6)";
				GetId("MusicJournal").style.color="rgba(255,255,255,.4)";
			break;
		case 3:	GetId("Also").style.color="rgba(255,255,255,.6)";
				GetId("Selfless").style.color="rgba(255,255,255,.8)";
				GetId("Recommand").style.color="rgba(255,255,255,1)";
				GetId("GuessLike").style.color="rgba(255,255,255,.8)";
				GetId("MusicJournal").style.color="rgba(255,255,255,.6)";
			break;
		case 4:	GetId("Also").style.color="rgba(255,255,255,.4)";
				GetId("Selfless").style.color="rgba(255,255,255,.6)";
				GetId("Recommand").style.color="rgba(255,255,255,.8)";
				GetId("GuessLike").style.color="rgba(255,255,255,1)";
				GetId("MusicJournal").style.color="rgba(255,255,255,.8)";
			break;
		default:GetId("Also").style.color="rgba(255,255,255,.2)";
				GetId("Selfless").style.color="rgba(255,255,255,.4)";
				GetId("Recommand").style.color="rgba(255,255,255,.6)";
				GetId("GuessLike").style.color="rgba(255,255,255,.8)";
				GetId("MusicJournal").style.color="rgba(255,255,255,1)";
			break;
	}
}
function ColorForUs(num){
	switch (num){
		case 1:	GetId("Server").style.color="rgba(255,255,255,1)";
				GetId("Privacy").style.color="rgba(255,255,255,.8)";
				GetId("Communicate").style.color="rgba(255,255,255,.6)";
			break;
		case 2:	GetId("Server").style.color="rgba(255,255,255,.8)";
				GetId("Privacy").style.color="rgba(255,255,255,1)";
				GetId("Communicate").style.color="rgba(255,255,255,.8)";
			break;
		default:GetId("Server").style.color="rgba(255,255,255,.6)";
				GetId("Privacy").style.color="rgba(255,255,255,.8)";
				GetId("Communicate").style.color="rgba(255,255,255,1)";
			break;
	}
}

function ColorSL(num){
	switch (num){
		case 1:	GetId("New").style.color="rgba(255,255,255,1)";
				GetId("Hot").style.color="rgba(255,255,255,.8)";
				GetId("Kind").style.color="rgba(255,255,255,.6)";
			break;
		case 2:	GetId("New").style.color="rgba(255,255,255,.8)";
				GetId("Hot").style.color="rgba(255,255,255,1)";
				GetId("Kind").style.color="rgba(255,255,255,.8)";
			break;
		default:GetId("New").style.color="rgba(255,255,255,.6)";
				GetId("Hot").style.color="rgba(255,255,255,.8)";
				GetId("Kind").style.color="rgba(255,255,255,1)";
			break;
	}
}
function ColorMLL(num){
	switch (num){
		case 1:	GetId("Found").style.color="rgba(255,255,255,1)";
				GetId("Similar").style.color="rgba(255,255,255,.8)";
				GetId("Concerned").style.color="rgba(255,255,255,.6)";
			break;
		case 2:	GetId("Found").style.color="rgba(255,255,255,.8)";
				GetId("Similar").style.color="rgba(255,255,255,1)";
				GetId("Concerned").style.color="rgba(255,255,255,.8)";
			break;
		default:GetId("Found").style.color="rgba(255,255,255,.6)";
				GetId("Similar").style.color="rgba(255,255,255,.8)";
				GetId("Concerned").style.color="rgba(255,255,255,1)";
			break;
	}
}