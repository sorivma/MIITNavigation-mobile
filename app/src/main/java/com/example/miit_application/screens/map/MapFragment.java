package com.example.miit_application.screens.map;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miit_application.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CircleMapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.mapview.MapView;

public class MapFragment extends Fragment {
    public MapView mapView;
    private MapObjectCollection mapObjects;
    private CameraPosition miitCameraPosition;
    private MapObjectTapListener mapObjectTapListener;

    private class TapedMapObjectInfoHolder{
        private String name;
        private String number;
        private int imageId;
        public TapedMapObjectInfoHolder(String buildingNumber, String buildingName, int imageName) {
            this.name = buildingName;
            this.number = buildingNumber;
            this.imageId = imageName;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }

        public int getImageId() {
            return imageId;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.initialize(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.getMap().setZoomGesturesEnabled(true);
        miitCameraPosition = new CameraPosition(new Point(55.788198,
                37.606859), 16.5f, 0.0f,
                0.0f);
        Animation transferAnimation = new Animation(Animation.Type.LINEAR, 0);
        mapView.getMap().move(
                miitCameraPosition,
                transferAnimation,
                null
        );
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        mapObjectTapListener = (mapObject, point) -> {
            TapedMapObjectInfoHolder infoHolder =
                    (TapedMapObjectInfoHolder) mapObject.getUserData();
            Dialog showDataDialog = new Dialog(getContext());
            
            showDataDialog.setContentView(R.layout.building_dialog);
            showDataDialog.show();
            TextView buildingName = showDataDialog.findViewById(R.id.building_name);
            TextView buildingNumber = showDataDialog.findViewById(R.id.building_number);
            ImageView imageView = showDataDialog.findViewById(R.id.building_image);
            Log.d("MAP DEBUG", String.valueOf(infoHolder.getImageId()));
            imageView.setImageResource(infoHolder.getImageId());

            buildingName.setText(infoHolder.getName());
            buildingNumber.setText(infoHolder.getNumber());
            return true;
        };

        createClickablePlaceMark(
                new Point(55.788001, 37.608018),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-1",
                        "Институт управления и цифровых технологий",
                        R.drawable.guk_1)
        );
        createClickablePlaceMark(
                new Point(55.788269, 37.609509),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-10",
                        "Административный корпус",
                        R.drawable.guk_1)
        );
        createClickablePlaceMark(
                new Point(55.788525, 37.608886),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-8",
                        "Гуманитарный институт",
                        R.drawable.guk_8)
        );
        createClickablePlaceMark(
                new Point(55.788363, 37.607673),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-14",
                        "Дом физики",
                        R.drawable.guk_15)
        );
        createClickablePlaceMark(
                new Point(55.787533, 37.606999),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-5",
                        "Дом химии",
                        R.drawable.guk_5)
        );
        createClickablePlaceMark(
                new Point(55.787806, 37.606379),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-6",
                        "Юридический институт",
                        R.drawable.guk_6)
        );
        createClickablePlaceMark(
                new Point(55.788358, 37.606137),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-2",
                        "Институт транспортной техники и систем управления",
                        R.drawable.guk_2)
        );
        createClickablePlaceMark(
                new Point(55.787665, 37.604295),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-3",
                        "Институт экономики и финансов",
                        R.drawable.guk_3)
        );
        createClickablePlaceMark(
                new Point(55.789051, 37.605194),
                mapObjectTapListener,
                new TapedMapObjectInfoHolder("ГУК-4",
                        "Институт транспортной техники и систем управления",
                        R.drawable.guk_4)
        );

    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private void createClickablePlaceMark(Point point,
                                          MapObjectTapListener mapTapListener,
                                          TapedMapObjectInfoHolder infoHolder)
    {
        CircleMapObject circle = mapObjects.addCircle(
                new Circle(point,10), Color.BLUE,2,Color.BLUE
        );

        circle.setUserData(infoHolder);
        circle.addTapListener(mapTapListener);
    }
}