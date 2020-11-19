# kata-logging

ログ設計を学ぶための教材です。

ECサイトっぽいアプリケーションがあり、

- カート注文サービス (net.unit8.kata.shopping.ShoppingApplication)
- 通貨変換サービス (net.unit.kata.trading.TradingApplication)

の2つのサービスを起動して使います。

それぞれIntelliJ IDEAから起動すると、カート注文サービスは8080ポート、通貨変換サービスは8081ポートでListenします。


## APIの実行

商品カートサービスに向かって、カートへ商品追加のAPIを投げてみましょう。

```
curl -i -XPOST -H 'Content-Type: application/json' -d '{"productCd": "P003", "amount": 1}'  http://localhost:8080/cart/items
```

カートの中身は、以下のAPIで確認できます。

```
curl -i http://localhost:8080/cart
```

カートをチェックアウトして注文します。

```
curl -i -XPUT http://localhost:8080/cart/checkout
```

## エラーの調査

おそらく、起動して1回めのカートチェックアウトは、エラーになります。
その原因を出力されたログから調査してみましょう。

## ログを出力する

ログをきちんと出す状態にして、また調査してみましょう。