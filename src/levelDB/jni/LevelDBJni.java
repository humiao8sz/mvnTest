package levelDB.jni;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.fusesource.leveldbjni.JniDBFactory;
import org.fusesource.leveldbjni.internal.NativeDB.DBException;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.ReadOptions;
import org.iq80.leveldb.WriteOptions;

public class LevelDBJni {
	JniDBFactory factory = JniDBFactory.factory;
	
	File getTestDirectory(String name) throws IOException {
		File rc = new File(new File("test-data"), name);
		factory.destroy(rc, new Options().createIfMissing(true));
		rc.mkdirs();
		return rc;
	}

	public void testOpen() throws IOException {
		Options options = new Options().createIfMissing(true);

		File path = getTestDirectory("a");
		DB db = factory.open(path, options);

		db.close();

		// Try again.. this time we expect a failure since it exists.
		options = new Options().errorIfExists(true);
		try{
			factory.open(path, options);
		}catch(IOException e){
		}

	}

	public void testCRUD() throws IOException, DBException {

		Options options = new Options().createIfMissing(true);

		File path = getTestDirectory("a");
		DB db = factory.open(path, options);

		WriteOptions wo = new WriteOptions().sync(false);
		ReadOptions ro = new ReadOptions().fillCache(true).verifyChecksums(true);

		db.put(bytes("Tampa"), bytes("green"));
		db.put(bytes("London"), bytes("red"));
		db.put(bytes("New York"), bytes("blue"));

		db.delete(bytes("New York"), wo);

		// leveldb does not consider deleting something that does not exist an error.
		db.delete(bytes("New York"), wo);

		db.close();
	}

	byte[] bytes(String s){
		return s.getBytes();
	}
	
	public void testIterator() throws IOException, DBException {

		Options options = new Options().createIfMissing(true);

		File path = getTestDirectory("a");
		DB db = factory.open(path, options);

		db.put(bytes("Tampa"), bytes("green"));
		db.put(bytes("London"), bytes("red"));
		db.put(bytes("New York"), bytes("blue"));

		ArrayList<String> expecting = new ArrayList<String>();
		expecting.add("London");
		expecting.add("New York");
		expecting.add("Tampa");

		ArrayList<String> actual = new ArrayList<String>();

		DBIterator iterator = db.iterator();
		for(iterator.seekToFirst(); iterator.hasNext(); iterator.next()){
			actual.add(new String(iterator.peekNext().getKey()));
		}
		iterator.close();
		db.close();
	}

	public static void main(String[] args) {
		LevelDBJni levelDBJni = new LevelDBJni();
		try{
			levelDBJni.testCRUD();
		}catch(DBException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
