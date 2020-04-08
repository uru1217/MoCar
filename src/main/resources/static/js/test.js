navigator.geolocation.getCurrentPosition(function (pos) {
    let latitude = pos.coords.latitude;
    let longitude = pos.coords.longitude;

    let markers = [];

    const mapContainer = document.getElementById('map'),
        //지도의 중심좌표
        //lat~,long~으로 사용자 중심으로 찍힌다.
        mapOption = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level : 3
        };
    //주소-좌표 변환 객체를 생성한다.
    const geocoder = new kakao.maps.services.Geocoder();

    //지도를 생성한다.
    const map= new kakao.maps.Map(mapContainer, mapOption);

    //장소 검색 객체를 생성한다.
    const ps = new kakao.maps.services.Places();

    //검색 결과 목록이나 마커를 클릭했을때 장소명을 표출할 인포윈도우 생성
    const infowindow = new kakao.maps.infoWindow({zIndex:1});

    //현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시한다.
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);

    function searchAddrFromCoords(coords, callback) {
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    }

    //지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다.
    function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            for (let i=0; i<result.length; i++) {
                if (result[i].region.prototype === 'H') {
                    $("#keyword").val(result[i].address_name + " 카센터");

                    //키워드로 장소를 검색한다.
                    searchPlaces();
                    break;
                }
            }
        }

    }

    //키워드 검색을 요청하는 함수입니다.
    function searchPlaces() {
        const keyword = document.getElementById('keyword').value;
        if (!keyword.replace(/^\s+|\s+$/g, '')) {
            alert('키워드를 입력해주세요!');
            return false;
        }
        ps.keywordSearch(keyword, placesSearchCB)
    }
    //장소검색이 완료됬을때 호출되는 콜백함수 입니다.
    function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.setvice.Status.OK) {
            //정상적으로 검색완료시 검색목록과 마커 표출
            dispalyPlaces(data);

            //페이지 번호 표출
            displayPagination(pagination);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert("검색 결과가 존재하지 않습니다.");
            return;

        }  else if (status === kakao.maps.services.Status.ERROR) {
            alert("검색 결과 중 오류가 발생했습니다.");
            return;
        }
    }

    //검색 결과목록과 마커를 표출하는 함수입니다.
    function displayPlaces(places) {
        const listEl = document.getElementById('placesList'),
              menuEl = document.getElementById('menu_wrap'),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds(),
            listStr = '';
    }

    //검색결과 목록에 추가된 항목들을 제거한다.
    removeAllChildNods(listEl);

    //지도에 표시되고 있는 마커를 제거합니다.
    removeMaker();

    for (let i=0; i<places.length; i++) {
        //마커를 생성하고 지도에 표시
        const placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i),
            itemEl = getListItem(i,  places[i]); //검색결과 항목 요소를 생성

        //검색된 장소 위치를 기준으로 지도범위를 재설정 하기위해 LatLngBounds 객체에 좌표 추가
        bounds.extend(placePosition);

        //마커와 검색결과 항목에 mouseover 했을때
        //해당 장소에 인포윈도우에 장소명을 표시한다.
        //mouseout했을 때는 인포윈도우 닫음
        (function (marker, title) {
            kakao.maps.event.addListener(marker,'mouseover', function () {
                displayInfoWindow(marker, title);
            });

            kakao.maps.event.addListener(marker,  'mouseout', function () {
                infowindow.close();
            })
            itemEl.onmouseover = function () {
                displayInfowindow(marker,  title);
            }
            itemEl.onmouseout = function () {
                infowindow.close();
            }
        })(marker, places[i].place_name);
        fragment.appendChild(itemEl);
    }

    //검색결과 항목들을 검색결과 목록 Elemnet에 추가
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;


    //검색된 장소 위치를 기준으로 지도범위 재설정
    map.setBounds(bounds);

    //검색결과 항목을 Element로 변환하는 함수.
    function getListItem(index, places) {

        var el = document.createElement('li'),
            itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

        if (places.road_address_name) {
            itemStr += '    <span>' + places.road_address_name + '</span>' +
                '   <span class="jibun gray">' + places.address_name + '</span>';
        } else {
            itemStr += '    <span>' + places.address_name + '</span>';
        }

        itemStr += '  <span class="tel">' + places.phone + '</span>' +
            '</div>';

        el.innerHTML = itemStr;
        el.className = 'item';

        return el;
    }

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, idx, title) {
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
            imgOptions = {
                spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage
            });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker);  // 배열에 생성된 마커를 추가합니다

        return marker;
    }

    function removeMarker() {
        for (let i =0; i<markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    //검색결과 목록하단에 페이지 번호를 표시하는 함수
    function displayPagination(pagination) {
        const paginationEl = document.getElementById('pagination'),
            fragment = document.createDocumentFragment()
        let i;

        //기존에 추가된 페이지번호를 삭제한다.
        while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.accessKey.lastChild);
        }

        for (i = 1; i<= pagination.last; i++) {
            const el = document.createElement('a');
            el.herf = "#";
            el.innerHTML = i;

            if (i === pagination.current) {
                el.className='on';
            } else {
                el.onclick = (function (i) {
                    return function () {
                        pagination.gotoPage(i);
                    }
                })(i);
            }
        }
        paginationEl.appendChild(fragment);
    }

//검색결과 목록 또는 마커를 클릭했을때 호출되는 함수입니다.
    //인포윈도우에 장소명을 표시합니다.
    function displayInfowindow(marker, title) {
        const content = '<div style="padding:5px;z-index:1;">'+title+'</div>';

        infowindow.setContent(content);
        infowindow.open(map, marker);
    }
//검색결과 목록의 자식 Element를 제거하는 함수
    function removeAllChildNode(el) {
        while (el.hasChildNodes()) {
            el.removeChild(el.lastChild);
        }
    }






})