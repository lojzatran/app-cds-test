package lamtran.cds;

import io.sphere.sdk.models.Reference;
import io.sphere.sdk.products.*;
import io.sphere.sdk.producttypes.ProductType;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static io.sphere.sdk.json.SphereJsonUtils.readObjectFromResource;
import static java.util.stream.Collectors.toList;

public class ProductSyncMockUtils {
    public static final String PRODUCT_KEY_1_WITH_PRICES_RESOURCE_PATH = "product-key-1-with-prices.json";

    public static ProductDraftBuilder createProductDraftBuilder(@Nonnull final String jsonResourcePath,
                                                                @Nonnull final Reference<ProductType>
                                                                        productTypeReference) {
        final Product productFromJson = readObjectFromResource(jsonResourcePath, Product.class);
        final ProductData productData = productFromJson.getMasterData().getStaged();

        @SuppressWarnings("ConstantConditions") final List<ProductVariantDraft> allVariants = productData
                .getAllVariants().stream()
                .map(productVariant -> ProductVariantDraftBuilder.of(productVariant).build())
                .collect(toList());

        return ProductDraftBuilder
                .of(productTypeReference, productData.getName(), productData.getSlug(), allVariants)
                .metaDescription(productData.getMetaDescription())
                .metaKeywords(productData.getMetaKeywords())
                .metaTitle(productData.getMetaTitle())
                .description(productData.getDescription())
                .searchKeywords(productData.getSearchKeywords())
                .taxCategory(productFromJson.getTaxCategory())
                .state(productFromJson.getState())
                .key(productFromJson.getKey())
                .categories(
                        productData.getCategories().stream().map(Reference::toResourceIdentifier).collect(Collectors.toSet()))
                .categoryOrderHints(productData.getCategoryOrderHints())
                .publish(productFromJson.getMasterData().isPublished());
    }
}
