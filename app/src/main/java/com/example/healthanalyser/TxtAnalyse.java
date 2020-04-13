package com.example.healthanalyser;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TxtAnalyse extends AppCompatActivity {

    private EditText et;
    private Button submit;

    private boolean hashesCalculated = false;
    private int minWordSize = -1, maxWordSize = -1, polyHashBase = 31;
    private int polyHashMods[] = {1000000007, 1000000009};
    private int polyHashBaseInverse[] = {129032259, 838709685};
    private long polyHashPower[][];
    private HashMap<Long, Integer> phraseHashes = new HashMap<Long, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt_analyse);
        et = (EditText) findViewById(R.id.editText);
        submit = (Button) findViewById(R.id.button5);

        //string matching function

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = et.getText().toString();
                //call the function here where txt is the req string



                startActivity(new Intent(TxtAnalyse.this,TxtResults.class));
            }
        });

    }

    private void hashPhrases() {
        final String[] PHRASES = {"lonely", "sad", "woried", "unhappy", "want to die", "quit", "wish to die", "worried", "never be able", "depression", "depressed", "hate", "death", "grim reaper", "world is rotten", "don't blame anyone", "suicide", "burden", "goodbye", "heartbroken", "regrets", "regret", "melancholy", "sadness", "lover", "failure", "if only", "what might have been", "terminal", "bad news", "too late", "last words", "i am done", "i am tired", "hopeless", "helpless", "god", "not being forced", "no place for me", "heaven", "bullied", "unsuccessful", "strength", "scared", "help", "kill", "no longer", "darkness", "miserable", "sullen", "anxiety", "stress", "dejected", "rejected", "discouring", "disheartening", "colorless", "life", "dull", "boring", "bored", "negative", "oppresive", "disguting", "disgust", "sorrow", "tears", "low", "ailing", "troubling", "ill", "sick", "disease", "worse", "worst", "lost", "cry", "losing sleep", "past", "no future", "scars", "pain", "agony", "guilt", "guilty", "not okay", "need a break", "scream", "feel okay", "nothing is fine", "lost my mind", "eternal sleep", "no one cares", "escape reality", "broken", "alright", "forgive", "hurts", "sucks", "drowning"};
        final int[] WEIGHTS = {6, 5, 4, 5, 8, 4, 8, 4, 3, 6, 7, 5, 6, 7, 6, 5, 9, 7, 6, 7, 5, 5, 5, 6, 6, 6, 1, 1, 4, 5, 6, 8, 6, 6, 6, 6, 3, 3, 7, 6, 5, 5, 2, 5, 3, 7, 4, 4, 7, 6, 7, 7, 5, 5, 6, 8, 5, 5, 6, 5, 4, 6, 5, 6, 6, 7, 7, 3, 5, 5, 6, 6, 6, 7, 7, 4, 5, 6, 3, 5, 6, 7, 8, 7, 7, 7, 7, 5, 5, 8, 8, 8, 8, 5, 5, 3, 4, 6, 7, 5};
        assert (PHRASES.length == WEIGHTS.length);

        for(String onePhrase : PHRASES) {
            onePhrase = onePhrase.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if(minWordSize == -1 || onePhrase.length() < minWordSize)
                minWordSize = onePhrase.length();
            if(maxWordSize == -1 || onePhrase.length() > maxWordSize)
                maxWordSize = onePhrase.length();
        }

        polyHashPower = new long[2][maxWordSize];
        for(int j = 0; j < 2; j++) {
            polyHashPower[j][0] = 1;
            for(int i = 1; i < maxWordSize; i++)
                polyHashPower[j][i] = (polyHashPower[j][i - 1] * polyHashBase) % polyHashMods[j];
        }

        int weightIndex = 0;
        for(String onePhrase : PHRASES) {
            onePhrase = onePhrase.replaceAll("[^a-zA-Z]", "").toLowerCase();
            long currentPhraseHash[] = new long[2];
            for(int i = 0, len = onePhrase.length(); i < len; i++) {
                char ch = onePhrase.charAt(i);
                int charValue = (ch - 'a' + 1);
                for(int j = 0; j < 2; j++)
                    currentPhraseHash[j] = (currentPhraseHash[j] + charValue * polyHashPower[j][i]) % polyHashMods[j];
            }
            Long finalHash = (currentPhraseHash[0] << 30) + currentPhraseHash[1];
            phraseHashes.put(finalHash, WEIGHTS[weightIndex]);
            weightIndex++;
        }

    }

    private int getMatches(String text) {
        text = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int matchCount = 0, len = text.length();
        if(len < minWordSize)
            return 0;
        int startSize = minWordSize, endSize = Math.min(maxWordSize + 1, len);
        for(int segmentSize = startSize; segmentSize < endSize; segmentSize++) {

            long currentPhraseHash[] = new long[2];
            for(int i = 0; i < segmentSize; i++) {
                char ch = text.charAt(i);
                int charValue = (ch - 'a' + 1);
                for(int j = 0; j < 2; j++)
                    currentPhraseHash[j] = (currentPhraseHash[j] + charValue * polyHashPower[j][i]) % polyHashMods[j];
            }
            long finalHash = (currentPhraseHash[0] << 30) + currentPhraseHash[1];
            matchCount += phraseHashes.getOrDefault(finalHash, 0);

            for(int i = segmentSize; i < len; i++) {
                char chadd = text.charAt(i), chremove = text.charAt(i - segmentSize);
                int charAddValue = (chadd - 'a' + 1), charRemoveValue = (chremove - 'a' + 1);
                for(int j = 0; j < 2; j++) {
                    currentPhraseHash[j] = (currentPhraseHash[j] - charRemoveValue + polyHashMods[j]) % polyHashMods[j];    // Remove previous character
                    currentPhraseHash[j] = (currentPhraseHash[j] * polyHashBaseInverse[j]) % polyHashMods[j];   // Shift left
                    currentPhraseHash[j] = (currentPhraseHash[j] + charAddValue * polyHashPower[j][segmentSize - 1]) % polyHashMods[j]; // Add next character
                }
                finalHash = (currentPhraseHash[0] << 30) + currentPhraseHash[1];
                matchCount += phraseHashes.getOrDefault(finalHash, 0);
            }

        }
        return matchCount;
    }
}
