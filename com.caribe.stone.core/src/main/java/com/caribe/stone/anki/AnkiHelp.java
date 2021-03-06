package com.caribe.stone.anki;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class AnkiHelp {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		String property = null;

//		property = "c:/Users/yan.zhang/Documents/Anki/User 1";
		property = "/Users/thinkdeeply/Documents/Anki/User 1";
		// property = "/Users/thinkdeeply/Documents/Anki/Fiona";
		AnkiSettings settings = new AnkiSettings(property);

		DBUtils db = new DBUtils(settings);
		Dao dao = new Dao(db);
		QueryBean bean = new QueryBean();
		// bean.setDeckId(1L);
		// bean.setDeckId(1394371108962L);
		// bean.setDeckId(1L);//ESL
		// 1395672679482 tmp
		// 1394371108962L study
		// 1392112698116 zzz
		// 1395208211479 cloze
		List<Note> notes = dao.getAllNotesByCondition(bean);
		System.out.println("There are "+notes.size()+" words.");

		PhoneticService service = new PhoneticService();
		CambridgeVoiceService voiceService = new CambridgeVoiceService();
		YouDaoVoiceService youDaoVoiceService = new YouDaoVoiceService();
		IcibaVoiceService icibaVoiceService = new IcibaVoiceService();
		ExplainService explainService = new ExplainService();
		int i=0;
		for (Note note : notes) {
//			System.out.println(i++);
			String mediaPath = settings.getMediaPath();
			if (note.getFront().indexOf(" ") < 0 && note.getWord().indexOf("-")<0) {

				if(note.needToUpdateIndustry(explainService)){
					note.expandExample(icibaVoiceService);
				}

				if (note.needToUpdateExplain()) {
					String explain = explainService.getCollinsExplain(note.getWord());
					if (explain == null || explain.length() == 0) {
						System.out.println("----" + note.getWord());
						System.out.println(explain);
					}
					note.setBack(explain);
				}

				if (note.needToUpdatePhonetic()) {
					String phonetic = voiceService.getPhonetic(note.getWord());
					if (phonetic == null) {
						phonetic = service.getPhonetic(note);
					}
					note.setPhonetic(phonetic);
				}
				
				dao.update(note);
				
				if (note.needToAddVoice()) {

					if (!settings.containYoudao(note)) {
						// note.getWord().indexOf(" ") < 0 &&
						if (!settings.containUS(note)) {
							File mp3File;
							String saveFile = note.getFilePath(mediaPath);
							mp3File = voiceService.getVoiceFile(note, mediaPath);
							if (mp3File == null) {

								File ga = icibaVoiceService.getGAFromICB(note.getWord(), mediaPath);
								if (ga != null) {

									try {
										// String saveFile = mediaPath +
										// note.getWord()
										// + "-us" + ".mp3";
										File destFile = new File(saveFile);
										FileUtils.moveFile(ga, destFile);

									} catch (IOException e) {
										e.printStackTrace();
									}
								} else {
									youDaoVoiceService.downloadYoudaoVoice2(note, saveFile);
								}
							}
						}
						if (!settings.containUK(note)) {
							File mp3File;
							mp3File = voiceService.getUKVoice(note, mediaPath);
							if (mp3File == null) {
								String saveFile = mediaPath + note.getWord() + "-uk" + ".mp3";
								File be = icibaVoiceService.getRPFromICB(note.getWord(), mediaPath);
								if (be != null) {
									try {
										FileUtils.moveFile(be, new File(saveFile));
									} catch (IOException e) {
										e.printStackTrace();
									}
								} else {
									youDaoVoiceService.downloadYoudaoVoice1(note, saveFile);
								}
							}
						}
					}
				}
			}  else{
                File phraseMp3 = settings.getPhraseMp3(note);
              if(phraseMp3==null || !phraseMp3.exists())   {
                  DownLoadService.httpDownload(icibaVoiceService.downloadPhraseMp3(note.getWord()), phraseMp3.getAbsolutePath());
                }

            }
		}

		System.out.println("Over!");
	}

}