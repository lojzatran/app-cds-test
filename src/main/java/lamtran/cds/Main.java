package lamtran.cds;


import com.commercetools.sync.products.ProductSyncOptionsBuilder;
import com.commercetools.sync.products.utils.ProductSyncUtils;
import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.json.SphereJsonUtils;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.ProductDraft;
import io.sphere.sdk.producttypes.ProductType;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static lamtran.cds.ProductSyncMockUtils.PRODUCT_KEY_1_WITH_PRICES_RESOURCE_PATH;
import static lamtran.cds.ProductSyncMockUtils.createProductDraftBuilder;

public class Main {

    public static void main(final String[] args) {
        Product oldProduct = SphereJsonUtils.readObjectFromResource(PRODUCT_KEY_1_WITH_PRICES_RESOURCE_PATH, Product.class);
        final LocalizedString newName = LocalizedString.of(Locale.ENGLISH, "newName");
        final ProductDraft newProductDraft =
                createProductDraftBuilder(PRODUCT_KEY_1_WITH_PRICES_RESOURCE_PATH,
                        ProductType.referenceOfId("anyProductType"))
                        .name(newName)
                        .build();

        final List<UpdateAction<Product>> updateActions =
                ProductSyncUtils.buildActions(oldProduct, newProductDraft, ProductSyncOptionsBuilder.of(null).build(), new HashMap<>());
        System.out.println(updateActions);
    }
}
