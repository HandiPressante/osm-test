package com.handipressante.osmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView map = (MapView) findViewById(R.id.view);

        //map.setTileSource(TileSourceFactory.MAPNIK);

        //map.setUseDataConnection(false);
        final ITileSource tileSource = new XYTileSource("OSM", null,
                0, 18, 1024, ".png", new String[] {
                "http://a.tile.openstreetmap.org/",
                "http://b.tile.openstreetmap.org/",
                "http://c.tile.openstreetmap.org/" });

        map.setTileSource(tileSource);

        map.setMultiTouchControls(true);
        map.setBuiltInZoomControls(true);
        //map.setMaxZoomLevel(18);

        GeoPoint startPoint = new GeoPoint(48.120302, -1.633947);
        IMapController mapController = map.getController();
        mapController.setZoom(18);
        mapController.setCenter(startPoint);

        GeoPoint reuPos = new GeoPoint(48.12033, -1.63410);
        Marker reuMarker = new Marker(map);
        reuMarker.setPosition(reuPos);
        reuMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(reuMarker);

        map.invalidate();

        reuMarker.setTitle("No toilet here !");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
