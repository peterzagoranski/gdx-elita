package com.badlogic.gdx.elita.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.elita.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class DataService<TData> {

    private final String fileName;

    private TData data;

    public DataService(final Class<TData> clazz, final String fileName) throws ReflectionException {
        this.fileName = fileName;

        FileHandle profileDataFile = Gdx.files.local( this.fileName );
        // create the JSON utility object
        Json json = new Json();

        // check if the profile data file exists
        if( profileDataFile.exists() ) {

            // load the profile from the data file
            try {

                // read the file as text
                String profileAsText = profileDataFile.readString().trim();

                // decode the contents (if it's base64 encoded)
                if( profileAsText.matches( "^[A-Za-z0-9/+=]+$" ) ) {
                    Gdx.app.log( Game.LOG, "Persisted profile is base64 encoded" );
                    profileAsText = Base64Coder.decodeString( profileAsText );
                }

                // restore the state
                data = json.fromJson(clazz, profileAsText);

            } catch( Exception e ) {

                // log the exception
                Gdx.app.error( Game.LOG, "Unable to parse existing profile data file", e );

                // recover by creating a fresh new profile data file;
                // note that the player will lose all game progress
                data = ClassReflection.newInstance(clazz);
                save();

            }

        } else {
            // create a new profile data file
            data = ClassReflection.newInstance(clazz);
            save();
        }
    }

    public TData getData() {
        return this.data;
    }

    public void save() {
        // create the handle for the profile data file
        FileHandle dataFile = Gdx.files.local( fileName );
        Gdx.app.log( Game.LOG, "Saving data in: " + dataFile.path() );

        // create the JSON utility object
        Json json = new Json();

        // convert the given profile to text
        String dataAsText = json.toJson( data );

        // encode the text
        if( ! Game.DEV_MODE ) {
            dataAsText = Base64Coder.encodeString(dataAsText);
        }

        // write the profile data file
        dataFile.writeString( dataAsText, false );
    }
}
