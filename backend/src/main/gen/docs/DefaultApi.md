# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**plantsGet**](DefaultApi.md#plantsGet) | **GET** /plants | Alle Pflanzen abrufen |
| [**plantsIdDelete**](DefaultApi.md#plantsIdDelete) | **DELETE** /plants/{id} | Eine Pflanze nach ID löschen |
| [**plantsIdGet**](DefaultApi.md#plantsIdGet) | **GET** /plants/{id} | Eine Pflanze nach ID abrufen |
| [**plantsIdPut**](DefaultApi.md#plantsIdPut) | **PUT** /plants/{id} | Eine bestehende Pflanze aktualisieren |
| [**plantsPost**](DefaultApi.md#plantsPost) | **POST** /plants | Eine neue Pflanze erstellen |


<a name="plantsGet"></a>
# **plantsGet**
> List&lt;Plant&gt; plantsGet()

Alle Pflanzen abrufen

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<Plant> result = apiInstance.plantsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#plantsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Plant&gt;**](Plant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste aller Pflanzen |  -  |

<a name="plantsIdDelete"></a>
# **plantsIdDelete**
> plantsIdDelete(id)

Eine Pflanze nach ID löschen

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Object id = null; // Object | ID der zu löschenden Pflanze
    try {
      apiInstance.plantsIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#plantsIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | [**Object**](.md)| ID der zu löschenden Pflanze | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Pflanze erfolgreich gelöscht |  -  |
| **404** | Pflanze nicht gefunden |  -  |

<a name="plantsIdGet"></a>
# **plantsIdGet**
> Plant plantsIdGet(id)

Eine Pflanze nach ID abrufen

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Object id = null; // Object | ID der abzurufenden Pflanze
    try {
      Plant result = apiInstance.plantsIdGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#plantsIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | [**Object**](.md)| ID der abzurufenden Pflanze | |

### Return type

[**Plant**](Plant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Pflanze gefunden |  -  |
| **404** | Pflanze nicht gefunden |  -  |

<a name="plantsIdPut"></a>
# **plantsIdPut**
> Plant plantsIdPut(id, plant)

Eine bestehende Pflanze aktualisieren

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Object id = null; // Object | ID der zu aktualisierenden Pflanze
    Plant plant = new Plant(); // Plant | Pflanze mit den aktualisierten Informationen
    try {
      Plant result = apiInstance.plantsIdPut(id, plant);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#plantsIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | [**Object**](.md)| ID der zu aktualisierenden Pflanze | |
| **plant** | [**Plant**](Plant.md)| Pflanze mit den aktualisierten Informationen | |

### Return type

[**Plant**](Plant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Pflanze erfolgreich aktualisiert |  -  |
| **400** | Ungültige Anfrage |  -  |
| **404** | Pflanze nicht gefunden |  -  |

<a name="plantsPost"></a>
# **plantsPost**
> Plant plantsPost(plant)

Eine neue Pflanze erstellen

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Plant plant = new Plant(); // Plant | Die zu erstellende Pflanze
    try {
      Plant result = apiInstance.plantsPost(plant);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#plantsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **plant** | [**Plant**](Plant.md)| Die zu erstellende Pflanze | |

### Return type

[**Plant**](Plant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Pflanze erfolgreich erstellt |  -  |
| **400** | Ungültige Anfrage |  -  |

