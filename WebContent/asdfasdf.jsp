<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title>CSS Reference | display: table</title>
    <style>
      div {
        border: 1px solid ;
      }
      .jb-table {
        display: table;
        width: 100%;
      }
      .jb-table-row {
        display: table-row;
      }
      .jb-table-cell {
        display: table-cell;
        padding: 0px 20px;
        height: 150px;
      }
      .jb-top {
        vertical-align: top;
      }
      .jb-middle {
        vertical-align: middle;
      }
      .jb-bottom {
        vertical-align: bottom;
      }
    </style>
  </head>
  <body>
    <div class="jb-table">
      <div class="jb-table-row">
        <div class="jb-table-cell">
          <p>Lorem</p>
        </div>
        <div class="jb-table-cell">
          <p>Ipsum</p>
        </div>
        <div class="jb-table-cell">
          <p>Dolor</p>
        </div>
      </div>
      <div class="jb-table-row">
        <div class="jb-table-cell jb-top">
          <p>Lorem</p>
        </div>
        <div class="jb-table-cell jb-middle">
          <p>Ipsum</p>
        </div>
        <div class="jb-table-cell jb-bottom">
          <p>Dolor</p>
        </div>
      </div>
    </div>
  </body>
</html>